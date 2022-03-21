package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.dto.MatchDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.data.vo.SummonerVO

class SummonerResponse(
    val accountId: String, // riot account id
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String, // player uuid
    val summonerLevel: Long
) {
    fun toVO(name: String, grade: Int, klass: Int, number: Int): SummonerVO {
        return SummonerVO().apply {
            this.name = name
            this.grade = grade
            this.klass = klass
            this.number = number
            id = this@SummonerResponse.id
            summonerName = this@SummonerResponse.name
            summonerLevel = this@SummonerResponse.summonerLevel
            profileIconId = this@SummonerResponse.profileIconId
            playerUUID = this@SummonerResponse.puuid
        }
    }
}