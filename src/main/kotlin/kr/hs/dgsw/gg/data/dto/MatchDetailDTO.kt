package kr.hs.dgsw.gg.data.dto

import kr.hs.dgsw.gg.data.dto.match.ParticipantsDTO
import kr.hs.dgsw.gg.data.dto.match.TeamStatsDTO

class MatchDetailDTO(
    val gameDuration: Long,
    val gameEndTimeStamp: Long,
    val gameMode: String,
    val teams: List<TeamStatsDTO>,
    val participants: List<ParticipantsDTO>,
    val win: Boolean
) {
}