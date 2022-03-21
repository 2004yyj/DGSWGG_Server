package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.MatchVO
import org.springframework.data.jpa.repository.JpaRepository

interface MatchRepository: JpaRepository<MatchVO, String>