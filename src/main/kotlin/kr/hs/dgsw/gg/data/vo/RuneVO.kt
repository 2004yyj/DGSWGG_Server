package kr.hs.dgsw.gg.data.vo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "runes")
class RuneVO {
    @Id
    @Column(name = "id")
    var id: Int = 0
    @Column(name = "`key`")
    var key: String = ""
    @Column(name = "icon")
    var icon: String = ""
    @Column(name = "`name`")
    var name: String = ""
}