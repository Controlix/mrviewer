package be.mbict.mrviewer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/gitlab"])
class GitlabController(private val gitlabClient: GitlabClient) {

    @GetMapping("/mr")
    fun listAllMr(@RequestParam("state") state: String?) = gitlabClient.allMr(state)

    @GetMapping("/pr")
    fun listAllPr() = gitlabClient.allProjects()
}