package kr.hs.dgsw.gg.data.dto.match

import kr.hs.dgsw.gg.data.dto.match.BanDTO

class TeamStatsDTO(
    val win: Boolean,
    val teamId: Int,
    val baron: Int,
    val champion: Int,
    val dragon: Int,
    val tower: Int,
    val inhibitor: Int,
    val riftHerald: Int,
    val bans: List<BanDTO>,
)