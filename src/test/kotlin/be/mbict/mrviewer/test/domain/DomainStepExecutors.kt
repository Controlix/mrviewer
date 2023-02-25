package be.mbict.mrviewer.test.domain

import be.mbict.mrviewer.Mr
import be.mbict.mrviewer.MrEvent
import be.mbict.mrviewer.MrRepository
import be.mbict.mrviewer.test.steps.InputStepExecutor
import be.mbict.mrviewer.test.steps.OutputStepExecutor
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.jeasy.random.EasyRandom
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@ConditionalOnProperty(value = ["output"], havingValue = "domain")
class DomainOutputStepExecutor(private val mrRepository: MrRepository): OutputStepExecutor {

    override fun checkMrIsFirst(identifier: String) {
        assertThat(mrRepository.findAll().first().title).contains("AZertY")
    }
}
@Component
@ConditionalOnProperty(value = ["input"], havingValue = "domain")
class DomainInputStepExecutor(
    private val mrRepository: MrRepository,
    private val mapper: ObjectMapper
): InputStepExecutor {

    val easyRandom = EasyRandom()

    override fun addMr(identifier: String) {
        mrRepository.save(Mr(title = identifier, createdAt = LocalDateTime.now()))
    }

}
