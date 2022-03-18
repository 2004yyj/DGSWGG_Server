package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.RiotApi
import kr.hs.dgsw.gg.api.objects.RetrofitObject.retrofit
import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import org.springframework.stereotype.Service

@Service
class SummonerService {

    private val summonerApi = retrofit.create(RiotApi::class.java)

    fun getSummonerByName(summonerName: String): SummonerResponse {
        return runBlocking {
            val summoner = summonerApi.getSummonerByName(summonerName)
            summoner
        }
    }
}