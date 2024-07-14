package de.tholor.mtgTools.model.repositories

import de.tholor.mtgTools.model.Card
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : CrudRepository<Card, Long> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Card
}