package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.KrRiotApi
import kr.hs.dgsw.gg.api.objects.RetrofitObject.krRetrofit
import kr.hs.dgsw.gg.api.objects.RetrofitObject.krRiotApi
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import kr.hs.dgsw.gg.data.vo.toDTO
import kr.hs.dgsw.gg.repository.SummonerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException

@Service
class SummonerService(
    private val summonerRepository: SummonerRepository
) {
    fun getSummonerByName(summonerName: String): BaseDTO<SummonerDTO> {
        val summonerOpt = summonerRepository.getSummonerByName(summonerName)
        val summonerDTO =
            if (summonerOpt.isEmpty)
                getSummonerInfoFromRiotApi(summonerName).toDTO()
            else
                summonerOpt.get().toDTO()

        return BaseDTO(HttpStatus.OK.value(), "성공", summonerDTO)
    }

    fun postRefreshSummonerInfo(summonerName: String): BaseDTO<Nothing?> {
        val summonerResponse = getSummonerInfoFromRiotApi(summonerName)
        summonerRepository.save(summonerResponse.toVO())
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
}