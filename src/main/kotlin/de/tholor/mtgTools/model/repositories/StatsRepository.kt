package de.tholor.mtgTools.model.repositories

import de.tholor.mtgTools.model.Deck
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatsRepository : CrudRepository<Deck.StatsAgainst, Long> {
}