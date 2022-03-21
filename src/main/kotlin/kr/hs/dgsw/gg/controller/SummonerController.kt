package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.base.BaseDTO
import kr.hs.dgsw.gg.data.dto.SummonerDTO
import kr.hs.dgsw.gg.service.SummonerService
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
        @Param("summonerName") summonerName: String,
        @Param("grade") grade: Int,
        @Param("klass") klass: Int,
        @Param("number") number: Int,
        @Param("name") name: String
    ): ResponseEntity<BaseDTO<Nothing?>> {
        val response = summonerService.postRefreshSummonerInfo(summonerName, name, grade, klass, number)
        return ResponseEntity(response, HttpStatus.OK)
    }
}