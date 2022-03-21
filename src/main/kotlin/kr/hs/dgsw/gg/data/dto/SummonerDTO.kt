package kr.hs.dgsw.gg.data.dto

class SummonerDTO(
    val playerUUID: String,
    val name: String,
    val grade: Int,
    val klass: Int,
    val number: Int,
    val summonerLevel: Long,
    val profileIconId: Int,
    val createdAt: Long?,
    val updatedAt: Long?,
    val rankData: List<RankDTO> = ArrayList()
)