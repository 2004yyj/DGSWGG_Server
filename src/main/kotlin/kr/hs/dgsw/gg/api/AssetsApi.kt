package kr.hs.dgsw.gg.api

import kr.hs.dgsw.gg.api.objects.Contracts
import kr.hs.dgsw.gg.data.riot_response.RuneResponse
import retrofit2.http.GET

interface AssetsApi {
    @GET("/cdn/${Contracts.DataDragon_VERSION}/data/ko_KR/runesReforged.json")
    suspend fun getRunes(): List<RuneResponse>
}