package be.mbict.mrviewer

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/gitlab/hooks"])
class GitlabHook(val mrRepository: MrRepository) {

    @PostMapping
    fun anything(@RequestBody body: String) {
        println("Got new mr: [${body}]")
        mrRepository.save(Mr(payload = body))
    }
}