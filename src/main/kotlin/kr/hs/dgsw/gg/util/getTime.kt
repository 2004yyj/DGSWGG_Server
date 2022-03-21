package kr.hs.dgsw.gg.util

import java.time.LocalDateTime
import java.time.ZoneId

val LocalDateTime.time: Long
    get() = this.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond()