package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.objects.RetrofitObject.asiaRiotApi
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.MatchDetailDTO
import kr.hs.dgsw.gg.data.dto.MatchListDTO
import kr.hs.dgsw.gg.data.dto.match.PerksDTO
import kr.hs.dgsw.gg.data.vo.MatchUserVO
import kr.hs.dgsw.gg.data.vo.MatchVO
import kr.hs.dgsw.gg.repository.MatchRepository
import kr.hs.dgsw.gg.repository.MatchUserRepository
import kr.hs.dgsw.gg.repository.RuneRepository
import kr.hs.dgsw.gg.repository.SummonerRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException
import javax.transaction.Transactional

@Service
class MatchService(
    private val matchRepository: MatchRepository,
    private val matchUserRepository: MatchUserRepository,
    private val summonerRepository: SummonerRepository,
    private val runeRepository: RuneRepository
) {
    fun getAllBySummonerId(summonerId: String, pageable: Pageable): BaseDTO<List<MatchListDTO>> {
        val matches = matchUserRepository.findAllBySummonerId(summonerId, pageable).map {
            getListMatchFromRiotApi(summonerId, it.matchVO.id)
        }
        matches.toSortedSet { first, next -> (next!!.gameEndTimeStamp - first!!.gameEndTimeStamp).toInt() }
        return BaseDTO(HttpStatus.OK.value(), "성공", matches)
    }

    fun getMatchDetailByMatchId(matchId: String, summonerId: String): BaseDTO<MatchDetailDTO> {
        val match = getDetailMatchFromRiotApi(summonerId, matchId)
        return BaseDTO(HttpStatus.OK.value(), "성공", match)
    }

    @Transactional
    fun postMatchHistoryBySummonerId(summonerId: String): BaseDTO<Nothing?> {
        val summonerOpt = summonerRepository.getSummonerById(summonerId)

        val summonerVO = summonerOpt.orElseThrow {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "404 NOT FOUND"
            )
        }

        val matchResponse = getMatchIdListFromRiotApi(summonerVO.playerUUID)

        val matchVOList = matchResponse.map { matchId ->
            val matchVO = MatchVO()
            matchVO.id = matchId
            matchVO
        }

        val matchUserVOList = matchVOList.map {
            val matchUserVO = MatchUserVO()
            matchUserVO.matchVO = it
            matchUserVO.summonerId = summonerId
            matchUserVO
        }


        matchRepository.saveAllAndFlush(matchVOList)
        matchUserRepository.deleteAllBySummonerId(summonerId)
        matchUserRepository.saveAll(matchUserVOList)

        return BaseDTO(HttpStatus.OK.value(), "성공", null)
    }

    fun getListMatchFromRiotApi(summonerId: String, matchId: String): MatchListDTO {
        return runBlocking {
            try {
                val dto = asiaRiotApi.getMatchByMatchId(matchId).toListDTO(summonerId, matchId)
                dto.perks = setRunePath(dto.perks)
                dto
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message()
                )
            }
        }
    }

    fun getDetailMatchFromRiotApi(summonerId: String, matchId: String): MatchDetailDTO {
        return runBlocking {
            try {
                val dto = asiaRiotApi.getMatchByMatchId(matchId).toDetailDTO(summonerId)
                dto.participants = dto.participants.map {
                    it.perks = setRunePath(it.perks)
                    it
                }
                dto
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message()
                )
            }
        }
    }

    fun getMatchIdListFromRiotApi(playerUUID: String): List<String> {
        return runBlocking {
            try {
                asiaRiotApi.getMatchesByPlayerUUID(playerUUID, 0, 50)
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message()
                )
            }
        }
    }

    private fun setRunePath(dto: PerksDTO): PerksDTO {
        dto.styles = dto.styles.map { styleDTO ->
            styleDTO.selections.forEach {
                it.iconPath = getRune(it.perk)
            }
            styleDTO.stylePath = getRune(styleDTO.style)
            styleDTO
        }
        dto.statPerks = dto.statPerks.apply {
            iconPath.addAll(listOf(getRune(this.offense), getRune(this.flex), getRune(this.defense)))
        }
        return dto
    }

    private fun getRune(runeId: Int): String {
        val runeOpt = runeRepository.findById(runeId)
        return if (runeOpt.isPresent) {
            runeOpt.get().iconPath
        } else {
            ""
        }
    }
}