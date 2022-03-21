package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.objects.RetrofitObject.asiaRiotApi
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.MatchDTO
import kr.hs.dgsw.gg.data.riot_response.MatchResponse
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
    fun getAllBySummonerId(summonerId: String, pageable: Pageable): BaseDTO<List<MatchDTO>> {
        val matches = matchUserRepository.findAllBySummonerId(summonerId, pageable).map {
            getMatchFromRiotApi(it.matchVO.id)
        }
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


        matchRepository.saveAll(matchVOList)
        matchUserRepository.deleteAllBySummonerId(summonerId)
        matchUserRepository.saveAll(matchUserVOList)

        return BaseDTO(HttpStatus.OK.value(), "성공", null)
    }

    fun getMatchFromRiotApi(matchId: String): MatchDTO {
        return runBlocking {
            try {
                asiaRiotApi.getMatchByMatchId(matchId).toDTO()
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