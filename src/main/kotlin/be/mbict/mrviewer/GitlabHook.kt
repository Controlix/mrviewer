package be.mbict.mrviewer

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
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

/**
 * @see <a href="https://docs.gitlab.com/ee/user/project/integrations/webhook_events.html#merge-request-events">Merge request events</a>
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MrEvent(
    val objectKind: String,
    val eventType: String,
    val user: User,
    val project: Project,
    val objectAttributes: ObjectAttributes
)

data class User(
    val id: Int,
    val name: String,
    val username: String
)

data class Project(
    val id: Int,
    val name: String
)

data class ObjectAttributes(
    val action: String?,
    val state: String?
)
