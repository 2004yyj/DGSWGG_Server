package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.vo.RankVO

class RankResponse(
    private val tier: String,
    private val rank: String,
    private val queueType: String,
    private val leaguePoints: Int,
    private val wins: Int,
    private val losses: Int,
    private val summonerId: String,
) {
    fun toVO(): RankVO {
        return RankVO().apply {
            tier = this@RankResponse.tier
            rank = this@RankResponse.rank
            queueType = this@RankResponse.queueType
            leaguePoints = this@RankResponse.leaguePoints
            wins = this@RankResponse.wins
            losses = this@RankResponse.losses
            summonerId = this@RankResponse.summonerId
        }
    }
}