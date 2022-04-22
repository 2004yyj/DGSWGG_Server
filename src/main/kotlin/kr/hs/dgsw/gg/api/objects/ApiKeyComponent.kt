package kr.hs.dgsw.gg.api.objects

import kr.hs.dgsw.gg.api.objects.Contracts.API_KEY
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ApiKeyComponent {
    @Value("\${api-key}")
    fun getApiKey(value: String) {
        API_KEY = value
    }
}