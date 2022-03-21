package kr.hs.dgsw.gg.repository

import kr.hs.dgsw.gg.data.vo.SummonerVO
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SummonerRepository: JpaRepository<SummonerVO, String> {
    fun getSummonerBySummonerName(summonerName: String): Optional<SummonerVO>
}