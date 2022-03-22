package kr.hs.dgsw.gg.api.objects

import kr.hs.dgsw.gg.api.AsiaRiotApi
import kr.hs.dgsw.gg.api.KrRiotApi
import kr.hs.dgsw.gg.api.apikey.ApiKeyInterceptor
import kr.hs.dgsw.gg.api.objects.Contracts.ASIA_BASE_URL
import kr.hs.dgsw.gg.api.objects.Contracts.ASSETS_BASE_URL
import kr.hs.dgsw.gg.api.objects.Contracts.KR_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    val krRetrofit = Retrofit.Builder()
        .baseUrl(KR_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val asiaRetrofit = Retrofit.Builder()
        .baseUrl(ASIA_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val assetsRetrofit = Retrofit.Builder()
        .baseUrl(ASSETS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val krRiotApi = krRetrofit.create(KrRiotApi::class.java)
    val asiaRiotApi = asiaRetrofit.create(AsiaRiotApi::class.java)
}