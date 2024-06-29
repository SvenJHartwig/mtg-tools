package de.tholor.web.model.services

import de.tholor.web.model.Deck
import org.springframework.stereotype.Service

@Service
interface IDeckService {
    fun listDecks(): List<Deck>
    fun save(vararg decks: Deck): MutableIterable<Deck>
    fun findDeckNameByID(id: Long): String
}