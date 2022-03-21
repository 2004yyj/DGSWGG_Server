package kr.hs.dgsw.gg.data.vo

import kr.hs.dgsw.gg.data.base.BaseTimeVO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.util.time
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity(name = "summoner")
class SummonerVO: BaseTimeVO() {
    @Id
    @Column(name = "id")
    var id: String = ""
    @Column(name = "name")
    var name: String = ""
    @Column(name = "summoner_level")
    var summonerLevel: Long = 0L
    @Column(name = "profileIconId")
    var profileIconId: Int = 0
    @Column(name = "player_uuid")
    var playerUUID: String = ""
}

fun SummonerVO.toDTO(): SummonerDTO {
    return SummonerDTO(
        id,
        name,
        summonerLevel,
        profileIconId,
        playerUUID,
        createdAt.time,
        updatedAt.time
    )
}