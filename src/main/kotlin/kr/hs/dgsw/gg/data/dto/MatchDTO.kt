package kr.hs.dgsw.gg.data.dto

import kr.hs.dgsw.gg.data.riot_response.MatchResponse

class MatchDTO(
    val gameDuration: Long,
    val gameEndTimeStamp: Long,
    val mapId: Int,
    val queueId: Int,
    val teams: List<TeamStats>,
    val participants: List<Participants>
) {
    class TeamStats(
        val win: Boolean,
        val teamId: Int,
        val baron: Int,
        val champion: Int,
        val dragon: Int,
        val tower: Int,
        val inhibitor: Int,
        val riftHerald: Int,
        val bans: List<MatchResponse.Ban>,
    )

    class Participants(
        val teamId: Int,
        val kills: Int,
        val deaths: Int,
        val assists: Int,
        val kda: Double, // kda
        val wardsPlaced: Int,
        val goldEarned: Int,
        val playerUUID: String,
        val summonerName: String,
        val summoner1Id: Int, // 스펠1
        val summoner2Id: Int, // 스펠2
        val championName: String,
        val championId: Int,
        val doubleKills: Int,
        val tripleKills: Int,
        val quadraKills: Int,
        val pentaKills: Int,
        val item: List<Int>,
        val Perks: MatchResponse.Perks,
        val lanes: String,
        val totalDamageDealtToChampions: Int, // 가한피해량
        val totalDamageTaken: Int, // 받은피해량
        val neutralMinionsKilled: Int,
        val totalMinionsKilled: Int,
        val championTransform: Int // 케인의 형태를 위한 값임. (기본값 - 0, 그암 - 1, 다르킨 - 2)
    )

    class StatPerks(
        val defense: Int,
        val flex: Int,
        val offense: Int
    )

    class Style(
        val description: String,
        val selections: List<MatchResponse.Selection>,
        val style: Int
    )
}