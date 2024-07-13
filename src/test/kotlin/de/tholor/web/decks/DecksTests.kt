package de.tholor.web.decks

import de.tholor.web.mockComponents.MockCardRepo
import de.tholor.web.mockComponents.MockDeckRepo
import de.tholor.web.mockComponents.MockLegalityRepo
import de.tholor.web.mockComponents.MockStatsRepo
import de.tholor.web.model.Card
import de.tholor.web.model.Deck
import de.tholor.web.model.services.CardService
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.decks.controllers.CardGridRow
import de.tholor.web.pages.decks.controllers.DecksController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecksTests {
    private val decksController =
        DecksController(DeckService(MockDeckRepo(), MockStatsRepo()), CardService(MockCardRepo(), MockLegalityRepo()))

    @Test
    fun testDecksController() {
        assertEquals(mutableMapOf<Card, CardGridRow>(), decksController.buildCardRowMap(Deck(-1, "Test")))
        val deck = Deck(-1, "Test")
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
}