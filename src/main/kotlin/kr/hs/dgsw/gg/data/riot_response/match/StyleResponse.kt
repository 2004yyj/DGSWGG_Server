package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.StyleDTO

class StyleResponse(
    val description: String,
    val selections: List<SelectionResponse>,
    val style: Int
) {
    fun toDTO(): StyleDTO {
        return StyleDTO(
            description,
            selections.map { it.toDTO() },
            style
        )
    }
}