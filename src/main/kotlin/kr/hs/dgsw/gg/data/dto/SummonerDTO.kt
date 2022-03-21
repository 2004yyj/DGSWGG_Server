package kr.hs.dgsw.gg.data.dto

class SummonerDTO(
    val id: String,
    val name: String,
    val summonerLevel: Long,
    val profileIconId: Int,
    val playerUUID: String,
    val createdAt: Long?,
    val updatedAt: Long?,
    val rankData: List<RankDTO> = ArrayList()
)