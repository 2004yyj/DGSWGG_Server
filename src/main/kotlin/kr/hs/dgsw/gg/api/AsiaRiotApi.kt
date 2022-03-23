package kr.hs.dgsw.gg.api

import kr.hs.dgsw.gg.data.riot_response.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AsiaRiotApi {
    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatchesByPlayerUUID(
        @Path("puuid") playerUUID: String,
        @Query("start") start: Int,
        @Query("count") count: Int
    ): List<String>

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun getMatchByMatchId(
        @Path("matchId") matchId: String
    ): MatchResponse
}