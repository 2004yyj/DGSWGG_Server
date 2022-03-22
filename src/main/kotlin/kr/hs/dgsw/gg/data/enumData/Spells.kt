package kr.hs.dgsw.gg.data.enumData

enum class Spells() {
    SummonerBarrier,
    SummonerBoost,
    SummonerDot,
    SummonerExhaust,
    SummonerFlash,
    SummonerHaste,
    SummonerHeal,
    SummonerSmite,
    SummonerTeleport,
    SummonerMana,
    SummonerSnowball,
    NothingCode;

    companion object {
        fun valueOf(value: Int): Spells {
            return when(value) {
                21 -> SummonerBarrier
                1 -> SummonerBoost
                14 -> SummonerDot
                3 -> SummonerExhaust
                4 -> SummonerFlash
                6 -> SummonerHaste
                7 -> SummonerHeal
                11 -> SummonerSmite
                12 -> SummonerTeleport
                13 -> SummonerMana
                32 -> SummonerSnowball
                else -> NothingCode
            }
        }
    }
}