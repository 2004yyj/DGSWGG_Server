package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.MatchUserVO
import kr.hs.dgsw.gg.data.vo.MatchVO
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface MatchUserRepository: JpaRepository<MatchUserVO, String> {
    fun getAllBySummonerId(summonerId: String, pageable: Pageable): List<MatchUserVO>
}