package be.mbict.mrviewer

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("gitlab")
interface GitlabClient {

    /**
     * @see <a href="https://docs.gitlab.com/ee/api/merge_requests.html#list-merge-requests">Gitlab Rest API: List merge requests</a>
     */
    // scope=all
    @RequestMapping(method = [RequestMethod.GET], value = ["/api/v4/merge_requests"])
    fun allMr(@RequestParam("state") state: String?): String //List<MergeRequest>

    @RequestMapping(method = [RequestMethod.GET], value = ["/api/v4/projects"])
    fun allProjects(): String //List<MergeRequest>
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MergeRequest(
    val projectId: String,
    val title: String,
    val mergeUser: MergeUser
)

data class MergeUser(
    val id: Int,
    val name: String,
    val username: String
)