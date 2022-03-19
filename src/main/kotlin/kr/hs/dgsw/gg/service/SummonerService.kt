package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.AsiaRiotApi
import kr.hs.dgsw.gg.api.KrRiotApi
import kr.hs.dgsw.gg.api.objects.RetrofitObject.asiaRetrofit
import kr.hs.dgsw.gg.api.objects.RetrofitObject.krRetrofit
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException

@Service
class SummonerService {

    private val krRiotApi = krRetrofit.create(KrRiotApi::class.java)
    private val asiaRiotApi = asiaRetrofit.create(AsiaRiotApi::class.java)

    fun getSummonerByName(summonerName: String): SummonerDTO {
        return runBlocking {
            try {
                val summoner = krRiotApi.getSummonerByName(summonerName)
                val playerUUID = summoner.puuid
                val matchesIds = asiaRiotApi.getMatchesByPlayerUUID(playerUUID, 0, 10)
                val matches = matchesIds.map { asiaRiotApi.getMatchByMatchId(it).toDTO() }
                summoner.toDTO(matches)
            } catch (e: HttpException) {
                throw ResponseStatusException(
                    HttpStatus.valueOf(e.code()),
                    e.message(),
                )
            }
        }
    }
}