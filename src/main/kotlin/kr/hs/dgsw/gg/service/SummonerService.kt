package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.objects.RetrofitObject.krRiotApi
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.data.riot_response.RankResponse
import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import kr.hs.dgsw.gg.data.vo.toDTO
import kr.hs.dgsw.gg.repository.RankRepository
import kr.hs.dgsw.gg.repository.SummonerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException
import javax.transaction.Transactional

@Service
class SummonerService(
    private val summonerRepository: SummonerRepository,
    private val rankRepository: RankRepository
) {
    fun getSummonerByName(summonerName: String): BaseDTO<SummonerDTO> {
        val summonerDTO = summonerRepository.getSummonerBySummonerName(summonerName).orElseThrow {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "404 NOT FOUND"
            )
        }.toDTO()

        return BaseDTO(HttpStatus.OK.value(), "성공", summonerDTO)
    }

    @Transactional
    fun postRefreshSummonerInfo(summonerName: String, name: String, grade: Int, klass: Int, number: Int): BaseDTO<Nothing?> {
        val summonerResponse = getSummonerInfoFromRiotApi(summonerName)
        summonerRepository.save(summonerResponse.toVO(name, grade, klass, number))

        val rankList = ArrayList<RankResponse>()
        rankList.addAll(getRankBySummonerIdFromRiotApi(summonerResponse.id))
        if (rankList.isEmpty()) {
            rankList.add(RankResponse(queueType = "SOLO", summonerId = summonerResponse.id))
            rankList.add(RankResponse(queueType = "FLEX", summonerId = summonerResponse.id))
        } else if(rankList.size == 1) {
            rankList.add(RankResponse(
                queueType = if (rankList.none { it.queueType == "SOLO" }) "SOLO" else "FLEX",
                summonerId = summonerResponse.id
            ))
        }

        val summonerVO = summonerRepository.getSummonerBySummonerName(summonerName).orElseThrow {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "404 NOT FOUND",
            )
        }
        rankRepository.deleteBySummonerVO(summonerVO)
        rankRepository.saveAll(rankList.map { it.toVO(summonerVO) })
        return BaseDTO(HttpStatus.OK.value(), "성공", null)
    }

    private fun getSummonerInfoFromRiotApi(summonerName: String): SummonerResponse {
        return runBlocking {
            try {
                krRiotApi.getSummonerByName(summonerName)
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message(),
                )
            }
        }
    }

    private fun getRankBySummonerIdFromRiotApi(summonerId: String): List<RankResponse> {
        return runBlocking {
            try {
                krRiotApi.getRankBySummonerId(summonerId)
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message(),
                )
            }
        }
    }
}