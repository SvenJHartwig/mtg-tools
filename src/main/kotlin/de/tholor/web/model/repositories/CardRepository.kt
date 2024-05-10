package de.tholor.web.model.repositories

import de.tholor.web.model.Card
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : CrudRepository<Card, Long> {
}