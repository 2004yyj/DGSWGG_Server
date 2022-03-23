package kr.hs.dgsw.gg.data.vo

import com.google.gson.Gson
import kr.hs.dgsw.gg.data.dto.MiniSeriesDTO
import kr.hs.dgsw.gg.data.dto.RankDTO
import kr.hs.dgsw.gg.data.dto.RankNoneSummonerDTO
import javax.persistence.*

@Table
@Entity(name = "ranks")
class RankVO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Column(name = "tier")
    var tier: String = ""
    @Column(name = "tier_id")
    var tierId: Int = 0
    @Column(name = "ranking")
    var rank: String = ""
    @Column(name = "ranking_id")
    var rankId: Int = 0
    @Column(name = "queue_type")
    var queueType: String = ""
    @Column(name = "league_points")
    var leaguePoints: Int = 0
    @Column(name = "wins")
    var wins: Int = 0
    @Column(name = "losses")
    var losses: Int = 0
    @Column(name = "miniSeries")
    var miniSeries: String? = ""

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "summoner_id")
    var summonerVO: SummonerVO? = null
}

fun RankVO.toDTO(): RankDTO {
    return RankDTO(
        id,
        summonerVO?.toNoRankDTO(),
        tier,
        rank,
        queueType,
        leaguePoints,
        wins,
        losses,
        Gson().fromJson(miniSeries, MiniSeriesDTO::class.java)
    )
}

fun RankVO.toNoneSummonerDTO(): RankNoneSummonerDTO {
    println(miniSeries)
    return RankNoneSummonerDTO(
        id,
        tier,
        rank,
        queueType,
        leaguePoints,
        wins,
        losses,
        Gson().fromJson(miniSeries, MiniSeriesDTO::class.java)
    )
}