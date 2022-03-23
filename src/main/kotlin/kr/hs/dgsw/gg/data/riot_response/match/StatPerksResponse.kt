package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.StatPerkDTO
import kr.hs.dgsw.gg.data.dto.match.StatPerksDTO

class StatPerksResponse(
    val defense: Int,
    val flex: Int,
    val offense: Int
) {
    fun toDTO(): StatPerksDTO {
        return StatPerksDTO(
            StatPerkDTO("defense", defense),
            StatPerkDTO("flex", flex),
            StatPerkDTO("offense", offense)
        )
    }
}