package de.tholor.web.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

@Entity
class Card(
    @Id
    @GeneratedValue
    private val id: Long,
    private val name: String
) : Serializable {

}