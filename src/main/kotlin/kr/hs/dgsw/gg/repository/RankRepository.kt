package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.RankVO
import kr.hs.dgsw.gg.data.vo.SummonerVO
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RankRepository: JpaRepository<RankVO, Int> {
    fun deleteBySummonerVO(summonerVO: SummonerVO)
    fun findAllByQueueType(queueType: String, pageable: Pageable): List<RankVO>
}