package kr.hs.dgsw.gg.data.vo

import kr.hs.dgsw.gg.data.base.BaseTimeVO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.data.dto.SummonerNoRankDTO
import kr.hs.dgsw.gg.util.time
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Table
@Entity(name = "summoner")
class SummonerVO: BaseTimeVO() {
    @Id
    @Column(name = "id")
    var id: String = ""
    @Column(name = "summoner_name")
    var summonerName: String = ""
    @Column(name = "name")
    var name: String = ""
    @Column(name = "grade")
    var grade: Int = 0
    @Column(name = "class")
    var klass: Int = 0
    @Column(name = "number")
    var number: Int = 0
    @Column(name = "summoner_level")
    var summonerLevel: Long = 0L
    @Column(name = "profileIconId")
    var profileIconId: Int = 0
    @Column(name = "player_uuid")
    var playerUUID: String = ""
    @OneToMany(mappedBy = "summonerVO")
    var rankList: List<RankVO> = ArrayList()
}

fun SummonerVO.toDTO(): SummonerDTO {
    return SummonerDTO(
        id,
        playerUUID,
        name,
        grade,
        klass,
        number,
        summonerLevel,
        profileIconId,
        createdAt.time,
        updatedAt.time,
        rankList.map { it.toNoneSummonerDTO() }
    )
}

fun SummonerVO.toNoRankDTO(): SummonerNoRankDTO {
    return SummonerNoRankDTO(
        id,
        playerUUID,
        name,
        grade,
        klass,
        number,
        summonerLevel,
        profileIconId,
        createdAt.time,
        updatedAt.time
    )
}