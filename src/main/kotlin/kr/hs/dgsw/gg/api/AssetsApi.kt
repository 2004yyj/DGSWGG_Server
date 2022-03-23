package kr.hs.dgsw.gg.api

import kr.hs.dgsw.gg.data.riot_response.RuneResponse
import kr.hs.dgsw.gg.data.riot_response.StyleResponse
import retrofit2.http.GET

interface AssetsApi {
    @GET("/latest/plugins/rcp-be-lol-game-data/global/ko_kr/v1/perks.json")
    suspend fun getRunes(): List<RuneResponse>

    @GET("/latest/plugins/rcp-be-lol-game-data/global/ko_kr/v1/perkstyles.json")
    suspend fun getRuneStyles(): StyleResponse
}