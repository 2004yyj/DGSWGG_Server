package kr.hs.dgsw.gg.data.dto.match

class ParticipantsDTO(
        val teamId: Int,
        val kills: Int,
        val deaths: Int,
        val assists: Int,
        val kda: Double, // kda
        val wardsPlaced: Int,
        val goldEarned: Int,
        val summonerName: String,
        val summonerSpells: List<String>,
        val championName: String,
        val championId: Int,
        val doubleKills: Int,
        val tripleKills: Int,
        val quadraKills: Int,
        val pentaKills: Int,
        val item: List<Int>,
        val perks: PerksDTO,
        val lanes: String,
        val totalDamageDealtToChampions: Int, // 가한피해량
        val totalDamageTaken: Int, // 받은피해량
        val neutralMinionsKilled: Int,
        val totalMinionsKilled: Int,
        val championTransform: Int // 케인의 형태를 위한 값임. (기본값 - 0, 그암 - 1, 다르킨 - 2)
)