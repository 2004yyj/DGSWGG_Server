package kr.hs.dgsw.gg.service

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.RankDTO
import kr.hs.dgsw.gg.data.vo.toDTO
import kr.hs.dgsw.gg.repository.RankRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class RankService(
    private val rankRepository: RankRepository
) {
    fun getAllRank(queueType: String, page: Int, size: Int): BaseDTO<List<RankDTO>> {
        val sortOrders = arrayListOf(
            Sort.Order(Sort.Direction.ASC, "tierId"),
            Sort.Order(Sort.Direction.ASC, "rankId"),
            Sort.Order(Sort.Direction.DESC, "leaguePoints"),
        )
        val pageable = PageRequest.of(page, size, Sort.by(sortOrders))

        val rankList = rankRepository.findAllByQueueType(queueType, pageable).map {
            it.toDTO()
        }
        return BaseDTO(HttpStatus.OK.value(), "성공", rankList)
    }
}