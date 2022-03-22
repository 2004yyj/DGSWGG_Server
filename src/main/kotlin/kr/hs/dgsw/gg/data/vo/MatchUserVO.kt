package kr.hs.dgsw.gg.data.vo

import javax.persistence.*

@Entity
@Table(name = "matches_user")
class MatchUserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Int = 0
    @ManyToOne(cascade = [CascadeType.DETACH])
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    var matchVO: MatchVO = MatchVO()
    @Column(name = "summoner_id")
    var summonerId: String = ""
}