package be.mbict.mrviewer.test.ui

import be.mbict.mrviewer.Mr
import be.mbict.mrviewer.MrEvent
import be.mbict.mrviewer.test.steps.InputStepExecutor
import be.mbict.mrviewer.test.steps.OutputStepExecutor
import be.mbict.mrviewer.test.steps.easyRandom
import com.fasterxml.jackson.databind.ObjectMapper
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlTable
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.assertj.core.condition.Not.not
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.function.Predicate

@Component
@ConditionalOnProperty(value = ["output"], havingValue = "mockmvc")
class MockMvcOutputStepExecutor(private val mockMvc: MockMvc, private val webClient: WebClient) : OutputStepExecutor {

    override fun checkMr(identifier: String, isFirstInList: Boolean) {
        val firstMr = webClient.getPage<HtmlPage>("/ui")
            .getHtmlElementById<HtmlTable>("mr-table")
            .bodies.first()
            .rows.first()
            .cells[2]
            .textContent

        assertThat(firstMr).`as`("MR with title '$identifier' should be first").apply { if (isFirstInList) contains(identifier) else doesNotContain(identifier) }
    }
}

@Component
@ConditionalOnProperty(value = ["input"], havingValue = "mockmvc")
class MockMvcInputStepExecutor(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper
) : InputStepExecutor {

    override fun addMr(identifier: String) {
        mockMvc.post("/gitlab/hooks/mr") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(easyRandom.nextObject(MrEvent::class.java).copy(objectKind = identifier))
        }.andExpect {
            status { isOk() }
        }
    }

    override fun commentMr(mr: Mr) {
        TODO("Not yet implemented")
    }
}