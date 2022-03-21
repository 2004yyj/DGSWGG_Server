package kr.hs.dgsw.gg.data.vo

import kr.hs.dgsw.gg.data.dto.RankDTO
import javax.persistence.*

@Table
@Entity(name = "ranks")
class RankVO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Column(name = "summoner_id")
    var summonerId: String = ""
    @Column(name = "summoner_name")
    var summonerName: String = ""
    @Column(name = "tier")
    var tier: String = ""
    @Column(name = "ranking")
    var rank: String = ""
    @Column(name = "queue_type")
    var queueType: String = ""
    @Column(name = "league_points")
    var leaguePoints: Int = 0
    @Column(name = "wins")
    var wins: Int = 0
    @Column(name = "losses")
    var losses: Int = 0
}

fun RankVO.toDTO(): RankDTO {
    return RankDTO(
        id,
        summonerId,
        summonerName,
        tier,
        rank,
        queueType,
        leaguePoints,
        wins,
        losses
    )
}