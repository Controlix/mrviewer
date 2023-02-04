package be.mbict.mrviewer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MrviewerApplication

fun main(args: Array<String>) {
	runApplication<MrviewerApplication>(*args)
}
