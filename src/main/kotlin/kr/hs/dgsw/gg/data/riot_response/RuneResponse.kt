package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.vo.RuneVO

class RuneResponse(
    val id: Int,
    val name: String,
    val iconPath: String,
) {
    fun toVO(): RuneVO {
        return RuneVO().apply {
            id = this@RuneResponse.id
            name = this@RuneResponse.name
            iconPath = this@RuneResponse.iconPath.replace("/lol-game-data/assets/v1/perk-images", "")
        }
    }
}