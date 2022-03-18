package kr.hs.dgsw.gg.controller

import kr.hs.dgsw.gg.data.riot_response.SummonerResponse
import kr.hs.dgsw.gg.service.SummonerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import retrofit2.HttpException

@RestController
class SummonerController(
    private val summonerService: SummonerService
) {
    @GetMapping("/summoner/{summonerName}")
    fun getSummonerByName(@PathVariable summonerName: String): ResponseEntity<SummonerResponse> {
        try {
            val summoner = summonerService.getSummonerByName(summonerName)
            return ResponseEntity(summoner, HttpStatus.OK)
        } catch (e: HttpException) {
            throw ResponseStatusException(
                HttpStatus.valueOf(e.code()),
                e.message(),
            )
        }
    }
}