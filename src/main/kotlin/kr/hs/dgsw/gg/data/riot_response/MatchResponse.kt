package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.dto.*
import kr.hs.dgsw.gg.data.dto.match.*
import kr.hs.dgsw.gg.data.enumData.GameMode
import kr.hs.dgsw.gg.data.enumData.QueueType
import kr.hs.dgsw.gg.data.enumData.Spells
import kr.hs.dgsw.gg.data.riot_response.match.InfoResponse

class MatchResponse(private val info: InfoResponse) {

    fun toDTO(summonerId: String): MatchDetailDTO {

        val teams = info.teams.map { team ->
            team.toDTO()
        }

        val gameMode = if (info.gameMode == GameMode.CLASSIC.name) {
            QueueType.valueOf(info.queueId).value
        } else {
            GameMode.valueOf(info.gameMode).value
        }

        var win = false

        val participants = info.participants.map {

            if(it.summonerId == summonerId) {
                win = it.win
            }

            val item = ArrayList<Int>()
            item.add(it.item0)
            item.add(it.item1)
            item.add(it.item2)
            item.add(it.item3)
            item.add(it.item4)
            item.add(it.item5)
            item.add(it.item6)

            val summonerSpells = arrayListOf(
                Spells.valueOf(it.summoner1Id).name,
                Spells.valueOf(it.summoner2Id).name
            )

            val lane = if (info.gameMode == GameMode.CLASSIC.name) {
                when(it.role) {
                    "CARRY" -> "BOTTOM"
                    "SUPPORT" -> "SUPPORT"
                    else -> it.lane
                }
            } else {
                "NOTHING"
            }

            ParticipantsDTO(
                it.teamId,
                it.kills,
                it.deaths,
                it.assists,
                it.challenges?.kda?:0.0,
                it.challenges?.wardsPlaced?:0,
                it.goldEarned,
                it.summonerName,
                summonerSpells,
                it.championName,
                it.championId,
                it.doubleKills,
                it.tripleKills,
                it.quadraKills,
                it.pentaKills,
                item,
                it.perks.toDTO(),
                lane,
                it.totalDamageDealtToChampions,
                it.totalDamageTaken,
                it.neutralMinionsKilled,
                it.totalMinionsKilled,
                it.championTransform,
            )
        }

        return MatchDetailDTO(
            info.gameDuration,
            info.gameEndTimestamp,
            gameMode,
            teams,
            participants,
            win
        )
    }
}