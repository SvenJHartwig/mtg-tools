package de.tholor.web.stats

import de.tholor.web.mockComponents.MockCardRepo
import de.tholor.web.mockComponents.MockDeckRepo
import de.tholor.web.mockComponents.MockLegalityRepo
import de.tholor.web.mockComponents.MockStatsRepo
import de.tholor.web.model.Deck
import de.tholor.web.model.services.CardService
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.stats.components.DeckScoreModel
import de.tholor.web.pages.stats.controllers.StatsController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StatsTests {


    private val deckRepository = MockDeckRepo()
    private val statsRepository = MockStatsRepo()
    private val statsController =
        StatsController(DeckService(deckRepository, statsRepository, CardService(MockCardRepo(), MockLegalityRepo())))

    @Test
    fun testListDecks() {
        assertTrue(statsController.listDecks().isEmpty())
        deckRepository.save(Deck(1, "Deck1"))
        assertEquals(1, statsController.listDecks().size)
        deckRepository.save(Deck(2, "Deck2"))
        assertEquals(2, statsController.listDecks().size)
    }

    @Test
    fun testBuildDeckList() {
        assertEquals(emptyList<Deck>(), statsController.buildDeckList(emptyList(), emptyList()))
        assertEquals(listOf(Deck(-1, "Deck1")), statsController.buildDeckList(listOf("Deck1"), emptyList()))
        assertEquals(
            listOf(Deck(-1, "Deck1"), Deck(-1, "Deck2")),
            statsController.buildDeckList(listOf("Deck1", "Deck2"), emptyList())
        )
        assertEquals(
            listOf(Deck(-1, "Deck1"), Deck(1, "Deck3")),
            statsController.buildDeckList(listOf("Deck1", "Deck3"), listOf(Deck(1, "Deck3")))
        )
    }

    @Test
    fun testAddScore() {
        statsController.addScore(emptyList())
        assertEquals(emptyList<Deck>(), deckRepository.findAll())
        statsController.addScore(listOf(DeckScoreModel("Deck1", 0)))
        assertEquals(listOf(Deck(1, "Deck1")), deckRepository.findAll())
        statsController.addScore(listOf(DeckScoreModel("Deck1", 0), DeckScoreModel("Deck2", 0)))
        assertEquals(listOf(Deck(1, "Deck1"), Deck(2, "Deck2")), deckRepository.findAll())
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1"), 0, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2"), 0, 0, 0))),
            deckRepository.findAll().toList()[1].stats
        )
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1"), 1, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2"), 0, 1, 0))),
            deckRepository.findAll().toList()[1].stats
        )
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1"), 2, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2"), 0, 2, 0))),
            deckRepository.findAll().toList()[1].stats
        )
    }

    @Test
    fun accumulateStatsTest() {
        assertEquals(Deck.StatsAgainst(0, Deck(-1, ""), 0, 0, 0), statsController.accumulateStats(emptyMap()))
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, ""), 0, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, Deck(-1, ""), 0, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, ""), 1, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, Deck(-1, ""), 1, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, ""), 1, 2, 0),
            statsController.accumulateStats(
                mapOf(
                    Pair(0, Deck.StatsAgainst(0, Deck(-1, ""), 1, 0, 0)),
                    Pair(1, Deck.StatsAgainst(0, Deck(-1, ""), 0, 2, 0))
                )
            )
        )
    }
}