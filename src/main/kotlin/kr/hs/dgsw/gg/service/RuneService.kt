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
    fun postRune() {
        if (runeRepository.findAll().size == 0) {
            val runeVO = ArrayList<RuneVO>()
            runBlocking {
                runeVO.addAll(assetsApi.getRunes().map { runeResponse ->
                    runeResponse.toVO()
                })

                runeVO.addAll(assetsApi.getRuneStyles().styles.map { runeResponse ->
                    runeResponse.toVO()
                })
            }
            runeRepository.saveAllAndFlush(runeVO)
        }
    }
}