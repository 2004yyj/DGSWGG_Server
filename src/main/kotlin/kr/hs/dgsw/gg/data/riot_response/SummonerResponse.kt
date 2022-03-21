package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.dto.MatchDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO

class SummonerResponse(
    val accountId: String, // riot account id
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String, // player uuid
    val summonerLevel: Long
) {
    fun toDTO(): SummonerDTO {
        return SummonerDTO(name, summonerLevel, profileIconId, puuid)
    }
}