package kr.hs.dgsw.gg.component

import kr.hs.dgsw.gg.service.RuneService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class RuneCommandLineRunner(
    private val runeService: RuneService
): CommandLineRunner {
    override fun run(vararg args: String?) {
        runeService.postRune()
    }
}