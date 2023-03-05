package be.mbict.mrviewer

import jakarta.persistence.*
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
class Mr(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var draft: Boolean = false,
    var title: String
) {

    override fun toString() = ReflectionToStringBuilder.reflectionToString(this)
}

interface MrRepository : JpaRepository<Mr, Int> {
    fun findByOrderByCreatedAtDesc(): List<Mr>
}

