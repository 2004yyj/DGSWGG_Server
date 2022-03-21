package kr.hs.dgsw.gg.api

import kr.hs.dgsw.gg.data.riot_response.RankResponse
import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KrRiotApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerByName(@Path("summonerName") summonerName: String): SummonerResponse

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    suspend fun getRankBySummonerId(@Path("encryptedSummonerId") encryptedSummonerId: String): RankResponse
}