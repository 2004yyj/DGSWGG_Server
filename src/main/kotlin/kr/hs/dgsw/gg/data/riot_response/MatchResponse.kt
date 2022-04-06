package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.dto.*
import kr.hs.dgsw.gg.data.dto.match.*
import kr.hs.dgsw.gg.data.enumData.GameMode
import kr.hs.dgsw.gg.data.enumData.QueueType
import kr.hs.dgsw.gg.data.enumData.Spells
import kr.hs.dgsw.gg.data.riot_response.match.InfoResponse
import kr.hs.dgsw.gg.data.riot_response.match.ParticipantsResponse

class MatchResponse(private val info: InfoResponse) {

    fun toDetailDTO(summonerId: String): MatchDetailDTO {

        val teams = info.teams.map { team ->
            team.toDTO()
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
                getLane(it),
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
            getGameMode(),
            teams,
            participants,
            win
        )
    }

    fun toListDTO(summonerId: String, matchId: String): MatchListDTO {
        val participantMapping = info.participants.filter { it.summonerId == summonerId }

        val me = participantMapping[0]

        val item = ArrayList<Int>()
        item.add(me.item0)
        item.add(me.item1)
        item.add(me.item2)
        item.add(me.item3)
        item.add(me.item4)
        item.add(me.item5)
        item.add(me.item6)

        val summonerSpells = arrayListOf(
            Spells.valueOf(me.summoner1Id).name,
            Spells.valueOf(me.summoner2Id).name
        )

        return MatchListDTO(
            matchId,
            info.gameDuration,
            info.gameEndTimestamp,
            getGameMode(),
            me.win,
            me.challenges?.kda?:0.0,
            me.kills,
            me.deaths,
            me.assists,
            item,
            summonerSpells,
            me.perks.toDTO(),
            me.championName,
            me.doubleKills,
            me.tripleKills,
            me.quadraKills,
            me.pentaKills,
            me.championTransform
        )
    }

    private fun getGameMode(): String {
        return if (info.gameMode == GameMode.CLASSIC.name) {
            QueueType.valueOf(info.queueId).value
        } else {
            try {
                GameMode.valueOf(info.gameMode).value
            } catch (e: Exception) {
                GameMode.NOTHING.value
            }
        }
    }

    private fun getLane(participants: ParticipantsResponse): String {
        return if (info.gameMode == GameMode.CLASSIC.name) {
            when(participants.role) {
                "CARRY" -> "BOTTOM"
                "SUPPORT" -> "SUPPORT"
                else -> participants.lane
            }
        } else {
            "NOTHING"
        }
    }
}