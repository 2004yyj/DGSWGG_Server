package kr.hs.dgsw.gg.data.base

class BaseDTO<T>(
    val status: Int,
    val message: String,
    val data: T
)