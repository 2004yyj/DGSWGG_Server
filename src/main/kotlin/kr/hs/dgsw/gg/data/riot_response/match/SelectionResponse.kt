package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.SelectionDTO

class SelectionResponse(val perk: Int) {
    fun toDTO(): SelectionDTO {
        return SelectionDTO(perk)
    }
}