package kr.hs.dgsw.gg.api.apikey

import kr.hs.dgsw.gg.api.objects.Contracts.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Riot-Token", API_KEY).build()
        return chain.proceed(request)
    }
}