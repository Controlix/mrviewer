package be.mbict.mrviewer.test.steps

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import org.aspectj.weaver.World

class CommonSteps(private val commonStepExecutor: CommonStepExecutor) {

    @Before
    @Given("no MR have been created yet")
    fun cleanAllMr() {
        commonStepExecutor.cleanAllMr()
    }

    @Given("some MR have already been created")
    fun createSomeMr() {
        commonStepExecutor.createSomeMr()
    }


    @Given("a MR that I haven't looked at yet")
    fun chooseExistingMr() {
        world.mr = commonStepExecutor.chooseRandomMr()
    }
}