package de.tholor.web.model.services

import de.tholor.web.model.Deck
import de.tholor.web.model.repositories.DeckRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeckService @Autowired internal constructor(
    private val deckRepository: DeckRepository
) : IDeckService {

    override fun listDecks(): List<Deck> {
        return deckRepository.findAll().toList()
    }
}