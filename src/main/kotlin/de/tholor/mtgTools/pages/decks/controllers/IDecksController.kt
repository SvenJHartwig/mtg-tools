package de.tholor.mtgTools.pages.decks.controllers

import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.Deck
import org.springframework.stereotype.Controller

@Controller
interface IDecksController {
    fun listDecks(): List<Deck>
    fun listCards(): List<Card>
    fun addCardToDeck(deck: Deck, card: Card)
    fun addDeck(name: String)
    fun buildCardRowMap(it: Deck): MutableMap<Card, CardGridRow>
    fun deleteCardFromDeck(deck: Deck, card: Card)
    fun isStandardLegal(deck: Deck): Boolean
}

data class CardGridRow(val card: Card, var number: Int)