package be.mbict.mrviewer.test.steps

import be.mbict.mrviewer.MrRepository
import org.springframework.stereotype.Component

@Component
class CommonStepExecutor(private val mrRepository: MrRepository) {
    fun cleanAllMr() = mrRepository.deleteAll()
}

interface OutputStepExecutor {
    fun checkMrIsFirst(identifier: String)
}

interface InputStepExecutor {
    fun addMr(identifier: String)
}