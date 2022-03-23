package kr.hs.dgsw.gg.data.dto

class RankDTO(
    var id: Int,
    var summoner: SummonerNoRankDTO?,
    var tier: String,
    var rank: String,
    var queueType: String,
    var leaguePoints: Int,
    var wins: Int,
    var losses: Int,
    var miniSeries: MiniSeriesDTO?
)