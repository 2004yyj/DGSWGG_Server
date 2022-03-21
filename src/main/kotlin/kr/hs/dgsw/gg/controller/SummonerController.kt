package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.service.SummonerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SummonerController(
    private val summonerService: SummonerService
) {
    @GetMapping("/summoner/{summonerName}")
    fun getSummonerByName(@PathVariable summonerName: String): ResponseEntity<BaseDTO<SummonerDTO>> {
        val summoner = summonerService.getSummonerByName(summonerName)
        return ResponseEntity(summoner, HttpStatus.OK)
    }

    @PostMapping("/summoner")
    fun postRefreshSummonerInfo(
        @RequestParam("summonerName") summonerName: String,
        @RequestParam("grade") grade: Int,
        @RequestParam("klass") klass: Int,
        @RequestParam("number") number: Int,
        @RequestParam("name") name: String
    ): ResponseEntity<BaseDTO<Nothing?>> {
        val response = summonerService.postRefreshSummonerInfo(summonerName, name, grade, klass, number)
        return ResponseEntity(response, HttpStatus.OK)
    }
}