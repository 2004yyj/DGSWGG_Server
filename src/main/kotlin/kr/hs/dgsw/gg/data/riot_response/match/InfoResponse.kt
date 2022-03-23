package kr.hs.dgsw.gg.data.riot_response.match

class InfoResponse(
        val gameDuration: Long, // 게임시간
        val gameEndTimestamp: Long, // 끝난시간
        val gameMode: String,
        val teams: List<TeamStatsResponse>,
        val queueId: Int,
        val participants: List<ParticipantsResponse>
)