package be.mbict.mrviewer

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository

@Entity
class Mr(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int?=null,
    val payload: String) {

}

interface MrRepository: JpaRepository<Mr, Int>

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MrEvent(
    val objectKind: String,
    val eventType: String,
    val user: User,
    val project: Project,
)

data class User(
    val id: Int,
    val name: String,
    val username: String
)

data class Project(
    val id: Int,
    val name: String,
    val namespace: String
)