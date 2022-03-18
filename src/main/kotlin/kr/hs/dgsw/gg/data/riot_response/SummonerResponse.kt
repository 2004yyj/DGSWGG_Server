package kr.hs.dgsw.gg.data.riot_response

class SummonerResponse(
    val accountId: String, // riot account id
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String, // player uuid
    val summonerLevel: Long
)