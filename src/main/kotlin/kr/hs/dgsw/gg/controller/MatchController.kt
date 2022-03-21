package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.MatchDTO
import kr.hs.dgsw.gg.repository.MatchRepository
import kr.hs.dgsw.gg.service.MatchService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchController(
    private val matchService: MatchService
) {
    @GetMapping("/match")
    fun getAllBySummonerId(
        @RequestParam("summonerId") summonerId: String,
        @PageableDefault() pageable: Pageable
    ): ResponseEntity<BaseDTO<List<MatchDTO>>> {
        val matchList = matchService.getAllBySummonerId(summonerId, pageable)
        return ResponseEntity(matchList, HttpStatus.OK)
    }

    @PostMapping("/match")
    fun postMatchHistoryBySummonerId(
        @RequestParam("summonerId") summonerId: String
    ): ResponseEntity<BaseDTO<Nothing?>> {
        val response = matchService.postMatchHistoryBySummonerId(summonerId)
        return ResponseEntity(response, HttpStatus.OK)
    }
}