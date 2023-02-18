package be.mbict.mrviewer.test.ui

import be.mbict.mrviewer.test.steps.OutputStepExecutor
import io.github.bonigarcia.wdm.WebDriverManager
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
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
        driver = ChromeDriver()
    }

    @PreDestroy
    fun close() {
        driver.quit()
    }

    @EventListener
    fun configureServerPort(event: ServletWebServerInitializedEvent) {
        port = event.webServer.port
    }

    override fun checkMrIsFirst(identifier: String) {
        driver.get("http://localhost:${port}/ui")
        val text = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/span")).text
        assertThat(text).contains(
            """
            "object_kind":"$identifier"
            """.trimIndent()
        )
    }
}