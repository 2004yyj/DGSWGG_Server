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
    @Column(name = "`name`")
    var name: String = ""
    @Column(name = "icon_path")
    var iconPath: String = ""
}