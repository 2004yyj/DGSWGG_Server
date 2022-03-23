package kr.hs.dgsw.gg.controller

import io.swagger.annotations.Api
import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.RankDTO
import kr.hs.dgsw.gg.service.RankService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RankController(
    private val rankService: RankService
) {
    @GetMapping("/rank")
    fun getAllRank(
        @RequestParam("queueType") queueType: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") size: Int
    ): ResponseEntity<BaseDTO<List<RankDTO>>> {
        val rankList = rankService.getAllRank(queueType, page, size)
        return ResponseEntity(rankList, HttpStatus.OK)
    }
}