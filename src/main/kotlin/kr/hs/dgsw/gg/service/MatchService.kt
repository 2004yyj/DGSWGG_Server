package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.objects.RetrofitObject.asiaRiotApi
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.MatchDetailDTO
import kr.hs.dgsw.gg.data.vo.MatchUserVO
import kr.hs.dgsw.gg.data.vo.MatchVO
import kr.hs.dgsw.gg.repository.MatchRepository
import kr.hs.dgsw.gg.repository.MatchUserRepository
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
    private val summonerRepository: SummonerRepository
) {
    fun getAllBySummonerId(summonerId: String, pageable: Pageable): BaseDTO<List<MatchDetailDTO>> {
        val matches = matchUserRepository.findAllBySummonerId(summonerId, pageable).map {
            getMatchFromRiotApi(summonerId, it.matchVO.id)
        }
        matches.toSortedSet { first, next -> (next!!.gameEndTimeStamp - first!!.gameEndTimeStamp).toInt() }
        return BaseDTO(HttpStatus.OK.value(), "성공", matches)
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

        val matchResponse = getMatchIdsFromRiotApi(summonerVO.playerUUID)

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

    fun getMatchFromRiotApi(summonerId: String, matchId: String): MatchDetailDTO {
        return runBlocking {
            try {
                val dto = asiaRiotApi.getMatchByMatchId(matchId).toDTO(summonerId)
                println(dto.gameEndTimeStamp)
                dto
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message()
                )
            }
        }
    }

    fun getMatchIdsFromRiotApi(playerUUID: String): List<String> {
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
}