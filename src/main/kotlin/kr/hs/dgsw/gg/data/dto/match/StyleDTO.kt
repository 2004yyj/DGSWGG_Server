package kr.hs.dgsw.gg.data.dto.match

class StyleDTO(
    val description: String,
    val selections: List<SelectionDTO>,
    val style: Int,
    var stylePath: String = ""
)