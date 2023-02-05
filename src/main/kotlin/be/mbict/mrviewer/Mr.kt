package be.mbict.mrviewer

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