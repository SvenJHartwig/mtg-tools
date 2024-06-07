package de.tholor.web.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Transient
import java.io.Serializable

@Entity
class Deck(
    @Id
    private val id: Long,
    public val name: String
) : Serializable {
    private lateinit var cardList: List<Card>

    class StatsAgainst : Serializable {
        private var wins: Int = 0
        private var losses: Int = 0
        private var draws: Int = 0
    }

    @Transient
    private lateinit var stats: Map<Long, StatsAgainst>

}