package kr.hs.dgsw.gg.api

import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerByName(@Path("summonerName") summonerName: String): SummonerResponse
}