package be.mbict.mrviewer.test.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class CreateMrSteps(
    private val outputStepExecutor: OutputStepExecutor,
    private val inputStepExecutor: InputStepExecutor) {

    @When("a user creates a MR")
    fun addMr() {
        inputStepExecutor.addMr("AZertY")
    }

    @Then("that MR appears on top of the list of MR")
    fun checkMr() {
        outputStepExecutor.checkMrIsFirst("AZertY")
    }
}