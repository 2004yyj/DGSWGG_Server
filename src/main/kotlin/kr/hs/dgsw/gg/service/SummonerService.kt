package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.SummonerApi
import kr.hs.dgsw.gg.api.objects.RetrofitObject.retrofit
import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException

@Service
class SummonerService {

    private val summonerApi = retrofit.create(SummonerApi::class.java)

    fun getSummonerByName(summonerName: String): SummonerResponse {
        return runBlocking {
            val summoner = summonerApi.getSummonerByName(summonerName)
            summoner
        }
    }
}