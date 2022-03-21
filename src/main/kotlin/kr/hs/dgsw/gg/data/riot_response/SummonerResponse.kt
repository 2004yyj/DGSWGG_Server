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
    fun toDTO(): SummonerDTO {
        return SummonerDTO(
            id, name, summonerLevel, profileIconId, puuid, null, null
        )
    }

    fun toVO(): SummonerVO {
        return SummonerVO().apply {
            id = this@SummonerResponse.id
            name = this@SummonerResponse.name
            summonerLevel = this@SummonerResponse.summonerLevel
            profileIconId = this@SummonerResponse.profileIconId
            playerUUID = this@SummonerResponse.puuid
        }
    }
}