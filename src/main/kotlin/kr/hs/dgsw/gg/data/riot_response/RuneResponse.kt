package kr.hs.dgsw.gg.data.riot_response

import kr.hs.dgsw.gg.data.riot_response.rune.SlotResponse
import kr.hs.dgsw.gg.data.vo.RuneVO

class RuneResponse(
    val id: Int,
    val key: String,
    val icon: String,
    val name: String,
    val slots: List<SlotResponse>
) {
    fun toVO(): RuneVO {
        return RuneVO().apply {
            id = this@RuneResponse.id
            key = this@RuneResponse.key
            icon = this@RuneResponse.icon
            name = this@RuneResponse.name
        }
    }
}