package de.tholor.mtgTools.decks

import de.tholor.mtgTools.mockComponents.*
import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.model.services.CardService
import de.tholor.mtgTools.model.services.DeckService
import de.tholor.mtgTools.pages.decks.controllers.CardGridRow
import de.tholor.mtgTools.pages.decks.controllers.DecksController
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DecksTests {
    private val cardService = CardService(MockCardRepo(), MockLegalityRepo())
    private val deckService = DeckService(MockDeckRepo(), MockStatsRepo(), cardService, MockSecurityService())
    private val decksController =
        DecksController(deckService, cardService, MockSecurityService())

    @Test
    fun testDecksController() {
        assertEquals(mutableMapOf<Card, CardGridRow>(), decksController.buildCardRowMap(Deck(-1, "Test", "test")))
        val deck = Deck(-1, "Test", "test")
        deck.cardList = mutableListOf(Card(name = "Test1"))
        assertEquals(
            mutableMapOf(Pair(Card(name = "Test1"), CardGridRow(Card(name = "Test1"), 1))),
            decksController.buildCardRowMap(deck)
        )
        deck.cardList.add(Card(name = "Test1"))
        assertEquals(
            mutableMapOf(Pair(Card(name = "Test1"), CardGridRow(Card(name = "Test1"), 2))),
            decksController.buildCardRowMap(deck)
        )
        deck.cardList.add(Card(name = "Test2"))
        assertEquals(
            mutableMapOf(
                Pair(Card(name = "Test1"), CardGridRow(Card(name = "Test1"), 2)),
                Pair(Card(name = "Test2"), CardGridRow(Card(name = "Test2"), 1))
            ),
            decksController.buildCardRowMap(deck)
        )
    }

    @Test
    fun testIsStandardLegal() {
        assertFalse(deckService.isStandardLegal(Deck(-1, "Test", "test")))
        val deck = Deck(-1, "Test", "test")
        deck.cardList = mutableListOf(Card(cardId = 4, name = "Island"))
        assertFalse(deckService.isStandardLegal(deck))
        for (i in 0..57) {
            deck.cardList.add(Card(cardId = 4, name = "Island"))
        }
        assertFalse(deckService.isStandardLegal(deck))
        deck.cardList.add(Card(cardId = 4, name = "Island"))
        assertTrue(deckService.isStandardLegal(deck))
        deck.cardList.add(Card(cardId = 2, name = "Dwarven soldier"))
        assertFalse(deckService.isStandardLegal(deck))
        deck.cardList.remove(Card(cardId = 2, name = "Dwarven soldier"))
        for (i in 0..4) {
            deck.cardList.add(Card(cardId = 1, name = "Sheoldred"))
        }
        assertFalse(deckService.isStandardLegal(deck))
    }
}