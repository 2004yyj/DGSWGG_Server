package kr.hs.dgsw.gg.data.riot_response.match

import kr.hs.dgsw.gg.data.dto.match.TeamStatsDTO

class TeamStatsResponse(
    val win: Boolean,
    val teamId: Int,
    val bans: List<BanResponse>,
    val objectives: ObjectivesResponse
) {
    fun toDTO(): TeamStatsDTO {
        return TeamStatsDTO(
            win,
            teamId,
            objectives.baron.kills,
            objectives.champion.kills,
            objectives.dragon.kills,
            objectives.tower.kills,
            objectives.inhibitor.kills,
            objectives.riftHerald.kills,
            bans.map { it.toDTO() }
        )
    }
}