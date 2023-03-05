package be.mbict.mrviewer.test.steps

import be.mbict.mrviewer.Mr
import be.mbict.mrviewer.MrRepository
import io.cucumber.java.Before
import jakarta.annotation.PostConstruct
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.FieldPredicates
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.function.Predicate

@Component
class CommonStepExecutor(private val mrRepository: MrRepository) {

    fun cleanAllMr() = mrRepository.deleteAll()

    fun createSomeMr() = mrRepository.saveAll(easyRandom.objects(Mr::class.java, 5).toList())
    fun chooseRandomMr() = mrRepository.findByOrderByCreatedAtDesc().last()
}

interface OutputStepExecutor {
    fun checkMr(identifier: String, isFirstInList: Boolean)
}

interface InputStepExecutor {
    fun addMr(identifier: String)
    fun commentMr(mr: Mr)
}

@Component
class StartupLogger(val env: Environment) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Log which executors where chosen to run the tests
     */
    @PostConstruct
    fun printExecutors() = listOf("input", "output").forEach { logger.info("$it: ${env.getProperty(it)}") }
}

object easyRandom : EasyRandom(EasyRandomParameters().randomize(FieldPredicates.named("createdAt"), { LocalDateTime.now() }))

object world {
    lateinit var mr: Mr
}