package kr.hs.dgsw.gg.data.vo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "matches_user")
class MatchUserVO {
    @Id
    @Column(name = "id")
    val id: Int = 0
    @ManyToOne
    @JoinColumn(name = "matches_id", referencedColumnName = "id")
    val matchVO: MatchVO = MatchVO()
    @Column(name = "summoner_id")
    val summonerId: String = ""
}