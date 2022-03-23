package kr.hs.dgsw.gg.data.riot_response.rank

class MiniSeriesResponse(
    private val target: Int,
    private val wins: Int,
    private val losses: Int,
    private val progress: String
) {
    override fun toString(): String {
        return "{ " +
                "\"target\": ${target}, " +
                "\"wins\": ${wins}, " +
                "\"losses\": ${losses}, " +
                "\"progress\": ${progress}, " +
        "}"
    }
}