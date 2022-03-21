package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.vo.RankVO

class RankResponse(
    val tier: String,
    val rank: String,
    val queueType: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    var summonerName: String,
    val summonerId: String,
) {
    fun toVO(): RankVO {
        return RankVO().apply {
            tier = this@RankResponse.tier
            rank = this@RankResponse.rank
            queueType = this@RankResponse.queueType
            leaguePoints = this@RankResponse.leaguePoints
            wins = this@RankResponse.wins
            losses = this@RankResponse.losses
            summonerName = this@RankResponse.summonerName
            summonerId = this@RankResponse.summonerId
        }
    }
}