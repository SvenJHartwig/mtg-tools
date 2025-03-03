package de.tholor.mtgTools.pages.decks.controllers

import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.model.services.CardService
import de.tholor.mtgTools.model.services.IDeckService
import de.tholor.mtgTools.shared.security.ISecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class DecksController @Autowired internal constructor(
    private val deckService: IDeckService,
    private val cardService: CardService,
    private val securityService: ISecurityService
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
        if (securityService.authenticatedUser == null) {
            return
        }
        val newDeck = Deck(-1, name, securityService.authenticatedUser!!.username)
        deckService.save(newDeck)
    }

    override fun buildCardRowMap(it: Deck): MutableMap<Card, CardGridRow> {
        val cardGridMap = mutableMapOf<Card, CardGridRow>()
        it.cardList.forEach { it2 ->
            if (cardGridMap[it2] == null) {
                cardGridMap[it2] = CardGridRow(it2, 1)
            } else {
                cardGridMap[it2]?.number = cardGridMap[it2]?.number!! + 1
            }
        }
        return cardGridMap
    }

    override fun deleteCardFromDeck(deck: Deck, card: Card) {
        deck.cardList.remove(card)
        deckService.save(deck)
    }

    override fun isStandardLegal(deck: Deck): Boolean {
        return deckService.isStandardLegal(deck)
    }
}