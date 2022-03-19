package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.dto.MatchDTO

class MatchResponse(private val info: Info) {

    fun toDTO(): MatchDTO {

        val teams = info.teams.map { team ->
            MatchDTO.TeamStats(
                team.win,
                team.teamId,
                team.objectives.baron.kills,
                team.objectives.champion.kills,
                team.objectives.dragon.kills,
                team.objectives.tower.kills,
                team.objectives.inhibitor.kills,
                team.objectives.riftHerald.kills,
                team.bans.map { ban ->
                    Ban(ban.championId)
                }
            )
        }

        val participants = info.participants.map {
            val item = ArrayList<Int>()
            item.add(it.item0)
            item.add(it.item1)
            item.add(it.item2)
            item.add(it.item3)
            item.add(it.item4)
            item.add(it.item5)
            item.add(it.item6)

            MatchDTO.Participants(
                it.teamId,
                it.kills,
                it.deaths,
                it.assists,
                it.challenges.kda,
                it.challenges.wardsPlaced,
                it.goldEarned,
                it.puuid,
                it.summonerName,
                it.summoner1Id,
                it.summoner2Id,
                it.championName,
                it.championId,
                it.doubleKills,
                it.tripleKills,
                it.quadraKills,
                it.pentaKills,
                item,
                it.perks,
                when(it.role) {
                    "CARRY" -> "BOTTOM"
                    "SUPPORT" -> "SUPPORT"
                    else -> it.lane
                },
                it.totalDamageDealtToChampions,
                it.totalDamageTaken,
                it.neutralMinionsKilled,
                it.totalMinionsKilled,
                it.championTransform
            )
        }

        return MatchDTO(
            info.gameDuration,
            info.gameEndTimeStamp,
            info.mapId,
            info.queueId,
            teams,
            participants
        )
    }

    class Info(
        val gameDuration: Long, // 게임시간
        val gameEndTimeStamp: Long, // 끝난시간
        val mapId: Int,
        val queueId: Int,
        val teams: List<TeamStats>,
        val participants: List<Participants>
    )

    class TeamStats(
        val win: Boolean,
        val teamId: Int,
        val bans: List<Ban>,
        val objectives: Objectives
    )

    class Ban(val championId: Int)

    class Objectives(
        val baron: Objective,
        val champion: Objective,
        val dragon: Objective,
        val inhibitor: Objective,
        val riftHerald: Objective,
        val tower: Objective
    )

    class Objective(
        val first: Boolean,
        val kills: Int
    )

    class Participants(
        val teamId: Int,
        val kills: Int,
        val deaths: Int,
        val goldEarned: Int,
        val assists: Int,
        val puuid: String,
        val summonerName: String,
        val summoner1Id: Int, // 스펠1
        val summoner2Id: Int, // 스펠2
        val championName: String,
        val championId: Int,
        val doubleKills: Int,
        val tripleKills: Int,
        val quadraKills: Int,
        val pentaKills: Int,
        val challenges: Challenge,
        val item0: Int,
        val item1: Int,
        val item2: Int,
        val item3: Int,
        val item4: Int,
        val item5: Int,
        val item6: Int, // 시야관련 아이템
        val perks: Perks,
        val lane: String,
        val role: String,
        val totalDamageDealtToChampions: Int, // 가한피해량
        val totalDamageTaken: Int, // 받은피해량
        val neutralMinionsKilled: Int,
        val totalMinionsKilled: Int,
        val championTransform: Int // 케인의 형태를 위한 값임. (기본값 - 0, 그암 - 1, 다르킨 - 2)
    )

    class Challenge(
        val kda: Double, // kda
        val wardsPlaced: Int,
    )

    class Perks(
        val statPerks: StatPerks, // 적응형 능력치
        val styles: List<Style> // 룬
    )

    class StatPerks(
        val defense: Int,
        val flex: Int,
        val offense: Int
    )

    class Style(
        val description: String,
        val selections: List<Selection>,
        val style: Int
    )

    class Selection(val perk: Int)
}