package kr.hs.dgsw.gg.data.enumData

enum class QueueType(val value: String) {
    SOLO_RANK("솔로 랭크"),
    FLEX_RANK("자유 랭크"),
    GENERAL("일반"),
    Nothing("알 수 없음");

    companion object {
        fun valueOf(value: Int): QueueType {
            return when(value) {
                420 -> SOLO_RANK
                430 -> GENERAL
                440 -> FLEX_RANK
                else -> Nothing
            }
        }
    }
}