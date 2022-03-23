package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.RankVO
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface RankRepository: JpaRepository<RankVO, Int> {
    fun deleteBySummonerId(summonerName: String)
    fun findAllByQueueType(sort: Sort, queueType: String): List<RankVO>
}