package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.BanDTO

class BanResponse(val championId: Int) {
    fun toDTO(): BanDTO {
        return BanDTO(championId)
    }
}