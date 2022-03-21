package kr.hs.dgsw.gg.data.dto

import javax.persistence.Column

class RankDTO(
    var id: Int,
    var summonerId: String,
    var summonerName: String,
    var tier: String,
    var rank: String,
    var queueType: String,
    var leaguePoints: Int,
    var wins: Int,
    var losses: Int
)