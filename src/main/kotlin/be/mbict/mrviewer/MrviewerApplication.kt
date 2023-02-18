package be.mbict.mrviewer

import feign.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
class MrviewerApplication

fun main(args: Array<String>) {
    runApplication<MrviewerApplication>(*args)
}
