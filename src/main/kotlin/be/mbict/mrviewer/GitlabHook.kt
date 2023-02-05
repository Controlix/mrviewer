package be.mbict.mrviewer

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/gitlab/hooks"])
class GitlabHook(val mrRepository: MrRepository) {

    @PostMapping("/mr")
    fun mr(@RequestBody event: MrEvent) {
        println("Got new mr: [${event}]")
        mrRepository.save(Mr(payload = event.toString()))
    }
}