package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.enumData.Rank
import kr.hs.dgsw.gg.data.enumData.RankType
import kr.hs.dgsw.gg.data.enumData.Tier
import kr.hs.dgsw.gg.data.riot_response.rank.MiniSeriesResponse
import kr.hs.dgsw.gg.data.vo.RankVO

class RankResponse(
    private val tier: String,
    private val rank: String,
    private val queueType: String,
    private val leaguePoints: Int,
    private val wins: Int,
    private val losses: Int,
    private val summonerId: String,
    private val miniSeries: MiniSeriesResponse?
) {
    fun toVO(): RankVO {
        return RankVO().apply {
            tier = this@RankResponse.tier
            tierId = Tier.valueOf(this@RankResponse.tier).tierId
            rank = this@RankResponse.rank
            rankId = Rank.valueOf(this@RankResponse.rank).rankId
            queueType = RankType.valueOf(this@RankResponse.queueType).type
            leaguePoints = this@RankResponse.leaguePoints
            wins = this@RankResponse.wins
            losses = this@RankResponse.losses
            summonerId = this@RankResponse.summonerId
            miniSeries = this@RankResponse.miniSeries?.toString()
        }
    }
}