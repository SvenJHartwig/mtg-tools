package de.tholor.web.pages.decks.controllers

import de.tholor.web.model.Card
import de.tholor.web.model.Deck
import org.springframework.stereotype.Controller

@Controller
interface IDecksController {
    fun listDecks(): List<Deck>
    fun listCards(): List<Card>
    fun addCardToDeck(deck: Deck, card: Card)
    fun addDeck(name: String)
}