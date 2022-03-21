package kr.hs.dgsw.gg.data.vo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "match")
class MatchVO {
    @Id
    @Column(name = "id")
    var id: String = ""
}