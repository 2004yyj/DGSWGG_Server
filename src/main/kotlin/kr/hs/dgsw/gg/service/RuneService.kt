package kr.hs.dgsw.gg.service

import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.gg.api.objects.RetrofitObject.assetsApi
import kr.hs.dgsw.gg.data.vo.RuneVO
import kr.hs.dgsw.gg.repository.RuneRepository
import org.springframework.stereotype.Service

@Service
class RuneService(
    private val runeRepository: RuneRepository
) {
    fun getRune() {
        if (runeRepository.findAll().size == 0) {
            val runeVO = ArrayList<RuneVO>()
            runBlocking {
                assetsApi.getRunes().forEach { runeResponse ->
                    runeVO.add(runeResponse.toVO())
                    runeResponse.slots.forEach { slotResponse ->
                        runeVO.addAll(slotResponse.runes.map { keyStoneResponse ->
                            keyStoneResponse.toVO()
                        })
                    }
                }
            }
            runeRepository.saveAllAndFlush(runeVO)
        }
    }
}