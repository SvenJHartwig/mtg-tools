package de.tholor.mtgTools.model.repositories

import de.tholor.mtgTools.model.Deck
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeckRepository : CrudRepository<Deck, Long> {
    fun findAllByNameIn(name: List<String>): List<Deck>
    fun findAllByUserName(name: String): List<Deck>
}