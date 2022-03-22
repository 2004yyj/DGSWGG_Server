package kr.hs.dgsw.gg.data.riot_response.rune

import kr.hs.dgsw.gg.data.vo.RuneVO

class KeyStoneResponse(
    val id: Int,
    val key: String,
    val icon: String,
    val name: String,
) {
    fun toVO(): RuneVO {
        return RuneVO().apply {
            id = this@KeyStoneResponse.id
            key = this@KeyStoneResponse.key
            icon = this@KeyStoneResponse.icon
            name = this@KeyStoneResponse.name
        }
    }
}
