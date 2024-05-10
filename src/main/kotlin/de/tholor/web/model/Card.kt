package de.tholor.web.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Card {
    @Id
    private var id: Long = 0

    private lateinit var name: String

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }

}