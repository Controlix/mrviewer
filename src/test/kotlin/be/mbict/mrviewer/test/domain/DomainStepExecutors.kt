package be.mbict.mrviewer.test.domain

import be.mbict.mrviewer.MrRepository
import be.mbict.mrviewer.test.steps.OutputStepExecutor
import org.assertj.core.api.Assertions
import org.springframework.stereotype.Component

@Component
// @Profile("domain")
class DomainOutputStepExecutor(private val mrRepository: MrRepository): OutputStepExecutor {

    override fun checkMrIsFirst(identifier: String) {
        Assertions.assertThat(mrRepository.findAll().first().payload).contains("AZertY")
    }
}