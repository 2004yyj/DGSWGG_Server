package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.MatchDetailDTO
import kr.hs.dgsw.gg.data.dto.MatchListDTO
import kr.hs.dgsw.gg.service.MatchService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MatchController(
    private val matchService: MatchService
) {
    @GetMapping("/match")
    fun getAllBySummonerId(
        @RequestParam("summonerId") summonerId: String,
        @PageableDefault() pageable: Pageable
    ): ResponseEntity<BaseDTO<List<MatchListDTO>>> {
        val matchList = matchService.getAllBySummonerId(summonerId, pageable)
        return ResponseEntity(matchList, HttpStatus.OK)
    }

    @GetMapping("/match/{matchId}")
    fun getMatchDetailByMatchId(
        @PathVariable matchId: String,
        @RequestParam("summonerId") summonerId: String
    ): ResponseEntity<BaseDTO<MatchDetailDTO>> {
        val match = matchService.getMatchDetailByMatchId(matchId, summonerId)
        return ResponseEntity(match, HttpStatus.OK)
    }

    @PostMapping("/match")
    fun postMatchHistoryBySummonerId(
        @RequestParam("summonerId") summonerId: String
    ): ResponseEntity<BaseDTO<Nothing?>> {
        val response = matchService.postMatchHistoryBySummonerId(summonerId)
        return ResponseEntity(response, HttpStatus.OK)
    }
}