package de.tholor.web.model.repositories

import de.tholor.web.model.Legalities
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LegalityRepository : CrudRepository<Legalities, Long> {
}