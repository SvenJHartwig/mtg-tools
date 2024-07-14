package de.tholor.mtgTools.model.repositories

import de.tholor.mtgTools.model.Legalities
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LegalityRepository : CrudRepository<Legalities, Long> {
}