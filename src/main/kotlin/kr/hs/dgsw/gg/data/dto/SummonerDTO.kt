package kr.hs.dgsw.gg.data.dto

class SummonerDTO(
    val name: String,
    val summonerLevel: Long,
    val profileIconId: Int,
    val match: List<MatchDTO>,
)