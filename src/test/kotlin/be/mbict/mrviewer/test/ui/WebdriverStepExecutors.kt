package be.mbict.mrviewer.test.ui

import be.mbict.mrviewer.test.steps.OutputStepExecutor
import io.github.bonigarcia.wdm.WebDriverManager
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["output"], havingValue = "webdriver")
class WebdriverOutputStepExecutor : OutputStepExecutor {

    private var port: Int = 0
    private lateinit var driver: WebDriver

    @PostConstruct
    fun init() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver(ChromeOptions().addArguments("--remote-allow-origins=*")) // https://stackoverflow.com/questions/75678572/java-io-ioexception-invalid-status-code-403-text-forbidden
    }

    @PreDestroy
    fun close() {
        driver.quit()
    }

    @EventListener
    fun configureServerPort(event: ServletWebServerInitializedEvent) {
        port = event.webServer.port
    }

    override fun checkMr(identifier: String, isFirstInList: Boolean) {
        driver.get("http://localhost:${port}/ui")
        val text = driver.findElement(By.id("mr-table")).findElement(By.xpath("tbody/tr/td[3]/span")).text

        assertThat(text).apply { if (isFirstInList) contains(identifier) else doesNotContain(identifier) }
    }
}