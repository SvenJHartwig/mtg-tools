package de.tholor.mtgTools.model.services

import de.tholor.mtgTools.model.Deck
import org.springframework.stereotype.Service

@Service
interface IDeckService {
    fun listDecks(): List<Deck>
    fun save(vararg decks: Deck): MutableIterable<Deck>
    fun findDeckNameByID(id: Long): String
    fun deleteDeck(deck: Deck)
    fun isStandardLegal(deck: Deck): Boolean
}