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

    override fun save(vararg decks: Deck): MutableIterable<Deck> {
        return deckRepository.saveAll(decks.toList())
    }

    fun listDecksWithNames(names: List<String>): List<Deck> {
        return deckRepository.findAllByNameIn(names).toList()
    }
}