package de.tholor.web.pages.decks.controllers

import de.tholor.web.model.Card
import de.tholor.web.model.Deck
import de.tholor.web.model.services.CardService
import de.tholor.web.model.services.IDeckService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class DecksController @Autowired internal constructor(
    private val deckService: IDeckService,
    private val cardService: CardService
) : IDecksController {
    override fun listDecks(): List<Deck> {
        return deckService.listDecks()
    }

    override fun listCards(): List<Card> {
        return cardService.listCards()
    }

    override fun addCardToDeck(deck: Deck, card: Card) {
        deck.cardList.add(card)
        deckService.save(deck)
    }

    override fun addDeck(name: String) {
        val newDeck = Deck(-1, name)
        deckService.save(newDeck)
    }
}