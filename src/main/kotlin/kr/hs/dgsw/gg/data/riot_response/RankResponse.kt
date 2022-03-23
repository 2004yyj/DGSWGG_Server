package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.enumData.Rank
import kr.hs.dgsw.gg.data.enumData.RankType
import kr.hs.dgsw.gg.data.enumData.Tier
import kr.hs.dgsw.gg.data.riot_response.rank.MiniSeriesResponse
import kr.hs.dgsw.gg.data.vo.RankVO
import kr.hs.dgsw.gg.data.vo.SummonerVO

class RankResponse(
    private val tier: String = "UNRANKED",
    private val rank: String = "UNRANKED",
    val queueType: String,
    private val leaguePoints: Int = 0,
    private val wins: Int = 0,
    private val losses: Int = 0,
    val summonerId: String,
    private val miniSeries: MiniSeriesResponse? = null
) {
    fun toVO(summonerVO: SummonerVO): RankVO {
        return RankVO().apply {
            tier = this@RankResponse.tier
            tierId = Tier.valueOf(this@RankResponse.tier).tierId
            rank = this@RankResponse.rank
            rankId = Rank.valueOf(this@RankResponse.rank).rankId
            queueType = RankType.valueOf(this@RankResponse.queueType).type
            leaguePoints = this@RankResponse.leaguePoints
            wins = this@RankResponse.wins
            losses = this@RankResponse.losses
            this.summonerVO = summonerVO
            miniSeries = this@RankResponse.miniSeries?.toString()
        }
    }
}