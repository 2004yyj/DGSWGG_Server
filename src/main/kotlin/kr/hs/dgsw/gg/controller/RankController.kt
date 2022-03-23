package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.RankDTO
import kr.hs.dgsw.gg.service.RankService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RankController(
    private val rankService: RankService
) {
    @GetMapping("/rank")
    fun getAllRank(queueType: String, pageable: Pageable): ResponseEntity<BaseDTO<List<RankDTO>>> {
        val rankList = rankService.getAllRank(queueType,pageable)
        return ResponseEntity(rankList, HttpStatus.OK)
    }
}