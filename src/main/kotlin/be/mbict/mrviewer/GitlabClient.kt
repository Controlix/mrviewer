package be.mbict.mrviewer

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime
import java.time.OffsetDateTime

@FeignClient("gitlab")
interface GitlabClient {

    /**
     * @see <a href="https://docs.gitlab.com/ee/api/merge_requests.html#list-merge-requests">Gitlab Rest API: List merge requests</a>
     */
    // scope=all
    @RequestMapping(method = [RequestMethod.GET], value = ["/api/v4/projects/{id}/merge_requests"])
    fun allMr(@PathVariable("id") projectId: Int, @RequestHeader("PRIVATE-TOKEN") @Value("gitlab.accessToken") token: String = "glpat-cvg7xkHyuHMFVgX_hWYg"): List<MergeRequest>

    @RequestMapping(method = [RequestMethod.GET], value = ["/api/v4/projects"])
    fun allProjects(@RequestParam("simple") simple: Boolean = true, @RequestHeader("PRIVATE-TOKEN") token: String = "glpat-VTU9ysJdPGSjCponfQj7"): List<Project>
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MergeRequest(
    val projectId: String,
    val title: String,
    val state: String,
    val draft: Boolean,
    val createdAt: LocalDateTime,
    val mergeUser: MergeUser?
)

data class MergeUser(
    val id: Int,
    val name: String,
    val username: String
)