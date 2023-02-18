package be.mbict.mrviewer.test.ui

import be.mbict.mrviewer.MrEvent
import be.mbict.mrviewer.test.steps.InputStepExecutor
import be.mbict.mrviewer.test.steps.OutputStepExecutor
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.jeasy.random.EasyRandom
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@Component
@ConditionalOnProperty(value = ["output"], havingValue = "ui")
class UiOutputStepExecutor(private val mockMvc: MockMvc) : OutputStepExecutor {

    override fun checkMrIsFirst(identifier: String) {
        mockMvc.get("/ui")
            .andExpect {
                status { isOk() }
                view { name("mr") }
                content {
                    string(Matchers.containsString("<td><span>MrEvent(objectKind=$identifier"))
                }
            }
    }
}

@Component
@ConditionalOnProperty(value = ["input"], havingValue = "ui")
class UiInputStepExecutor(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper
): InputStepExecutor {

    val easyRandom = EasyRandom()

    override fun addMr(identifier: String) {
        mockMvc.post("/gitlab/hooks/mr") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(easyRandom.nextObject(MrEvent::class.java).copy(objectKind = "AZertY"))
        }.andExpect {
            status { isOk() }
        }
    }
}