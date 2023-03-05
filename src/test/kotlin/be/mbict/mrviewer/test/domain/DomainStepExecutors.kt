package be.mbict.mrviewer.test.domain

import be.mbict.mrviewer.Mr
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
class DomainOutputStepExecutor(private val mrRepository: MrRepository) : OutputStepExecutor {

    override fun checkMr(identifier: String, isFirstInList: Boolean) {
        assertThat(mrRepository.findByOrderByCreatedAtDesc().first().title).apply { if (isFirstInList) contains(identifier) else doesNotContain(identifier) }
    }
}

@Component
@ConditionalOnProperty(value = ["input"], havingValue = "domain")
class DomainInputStepExecutor(
    private val mrRepository: MrRepository,
    private val mapper: ObjectMapper
) : InputStepExecutor {

    val easyRandom = EasyRandom()

    override fun addMr(identifier: String) {
        mrRepository.save(Mr(title = identifier, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now()))
    }

    override fun commentMr(mr: Mr) {
        mr.updatedAt = LocalDateTime.now()
        mrRepository.save(mr)
    }
}
