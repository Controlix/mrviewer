package be.mbict.mrviewer

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
class Mr(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    val createdAt: LocalDateTime,
    val draft: Boolean = false,
    val title: String
) {

}

interface MrRepository : JpaRepository<Mr, Int>

