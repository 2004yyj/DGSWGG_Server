package kr.hs.dgsw.gg.service

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.RankDTO
import kr.hs.dgsw.gg.data.vo.toDTO
import kr.hs.dgsw.gg.repository.RankRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class RankService(
    private val rankRepository: RankRepository
) {
    fun getAllRank(queueType: String, pageable: Pageable): BaseDTO<List<RankDTO>> {
        val tierSort = Sort
            .by(Sort.Direction.ASC, "tierId")
        val rankingSort = Sort
            .by(Sort.Direction.ASC, "rankId")
        val lpSort = Sort
            .by(Sort.Direction.DESC, "leaguePoints")
        val sort = tierSort.and(rankingSort).and(lpSort)

        val rankList = rankRepository.findAllByQueueType(sort, queueType, pageable).map {
            it.toDTO()
        }
        return BaseDTO(HttpStatus.OK.value(), "성공", rankList)
    }
}