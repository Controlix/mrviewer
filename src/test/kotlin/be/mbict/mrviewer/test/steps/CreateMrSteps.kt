package be.mbict.mrviewer.test.steps

import be.mbict.mrviewer.Mr
import be.mbict.mrviewer.MrEvent
import be.mbict.mrviewer.MrRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import org.jeasy.random.EasyRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

class CreateMrSteps(
    private val mrRepository: MrRepository) {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var mapper: ObjectMapper

    val easyRandom = EasyRandom()

    @Given("no MR have been created yet")
    fun cleanAllMr() {
        mrRepository.deleteAll()
    }

    @When("a user creates a MR")
    fun addMr() {
        mockMvc.post("/gitlab/hooks/mr") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(easyRandom.nextObject(MrEvent::class.java).copy(objectKind = "AZertY"))
        }.andExpect {
            status { isOk() }
        }
    }

    @Then("that MR appears on top of the list of MR")
    fun checkMr() {
        Assertions.assertThat(mrRepository.findAll().first().payload).contains("AZertY")
    }
}