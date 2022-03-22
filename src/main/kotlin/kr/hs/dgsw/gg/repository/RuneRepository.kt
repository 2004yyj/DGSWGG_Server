package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.RuneVO
import org.springframework.data.jpa.repository.JpaRepository

interface RuneRepository: JpaRepository<RuneVO, Int> {
}