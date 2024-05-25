package de.tholor.web.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Deck(
    @Id
    private val id: Long,
    private val name: String
) {
    private lateinit var cardList: List<Card>

    class StatsAgainst {
        private var wins: Int = 0
        private var losses: Int = 0
        private var draws: Int = 0
    }

    private lateinit var stats: Map<Long, StatsAgainst>

}