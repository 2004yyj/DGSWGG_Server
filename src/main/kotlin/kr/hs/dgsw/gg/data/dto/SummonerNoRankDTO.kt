package kr.hs.dgsw.gg.data.dto

class SummonerNoRankDTO(
    val id: String,
    val playerUUID: String,
    val name: String,
    val summonerName: String,
    val grade: Int,
    val klass: Int,
    val number: Int,
    val summonerLevel: Long,
    val profileIconId: Int,
    val createdAt: Long?,
    val updatedAt: Long?,
)