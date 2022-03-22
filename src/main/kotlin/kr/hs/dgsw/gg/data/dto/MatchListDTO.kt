package kr.hs.dgsw.gg.data.dto

import kr.hs.dgsw.gg.data.dto.match.PerksDTO

class MatchListDTO(
    val matchId: String,
    val gameDuration: Long,
    val gameEndTimeStamp: Long,
    val gameMode: String,
    val win: Boolean,
    val kda: Double, // kda
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val item: List<Int>,
    val summonerSpells: List<String>,
    val perks: PerksDTO,
    val championName: String,
    val doubleKills: Int,
    val tripleKills: Int,
    val quadraKills: Int,
    val pentaKills: Int,
    val championTransform: Int // 케인의 형태를 위한 값임. (기본값 - 0, 그암 - 1, 다르킨 - 2)
)