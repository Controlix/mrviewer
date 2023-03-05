package be.mbict.mrviewer.test.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class UpdateMrSteps(
    private val outputStepExecutor: OutputStepExecutor,
    private val inputStepExecutor: InputStepExecutor) {

    @When("a user comments that MR")
    fun commentMr() {
        inputStepExecutor.commentMr(world.mr)
    }

    @Then("that MR does not appear on top of the list of MR")
    fun checkMr() {
        outputStepExecutor.checkMr("AZertY", isFirstInList = false)
    }
}