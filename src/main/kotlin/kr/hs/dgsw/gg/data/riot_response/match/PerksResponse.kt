package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.PerksDTO

class PerksResponse(
    val statPerks: StatPerksResponse, // 적응형 능력치
    val styles: List<StyleResponse> // 룬
) {
    fun toDTO(): PerksDTO {
        return PerksDTO(
            statPerks.toDTO(),
            styles.map { it.toDTO() }
        )
    }
}