package kr.hs.dgsw.gg.data.riot_response.match

class ParticipantsResponse(
        val win: Boolean,
        val teamId: Int,
        val kills: Int,
        val deaths: Int,
        val goldEarned: Int,
        val assists: Int,
        val summonerName: String,
        val summonerId: String,
        val summoner1Id: Int, // 스펠1
        val summoner2Id: Int, // 스펠2
        val championName: String,
        val championId: Int,
        val doubleKills: Int,
        val tripleKills: Int,
        val quadraKills: Int,
        val pentaKills: Int,
        val challenges: ChallengeResponse?,
        val item0: Int,
        val item1: Int,
        val item2: Int,
        val item3: Int,
        val item4: Int,
        val item5: Int,
        val item6: Int, // 시야관련 아이템
        val perks: PerksResponse,
        val lane: String,
        val role: String,
        val totalDamageDealtToChampions: Int, // 가한피해량
        val totalDamageTaken: Int, // 받은피해량
        val neutralMinionsKilled: Int,
        val totalMinionsKilled: Int,
        val championTransform: Int, // 케인의 형태를 위한 값임. (기본값 - 0, 그암 - 1, 다르킨 - 2)
    )