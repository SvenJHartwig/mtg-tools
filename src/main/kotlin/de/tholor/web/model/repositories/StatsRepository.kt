package de.tholor.web.model.repositories

import de.tholor.web.model.Deck
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatsRepository : CrudRepository<Deck.StatsAgainst, Long> {
}