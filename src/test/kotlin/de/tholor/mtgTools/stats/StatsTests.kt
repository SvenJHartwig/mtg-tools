package de.tholor.mtgTools.stats

import de.tholor.mtgTools.mockComponents.*
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.model.services.CardService
import de.tholor.mtgTools.model.services.DeckService
import de.tholor.mtgTools.pages.stats.components.DeckScoreModel
import de.tholor.mtgTools.pages.stats.controllers.StatsController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StatsTests {


    private val deckRepository = MockDeckRepo()
    private val statsRepository = MockStatsRepo()
    private val statsController =
        StatsController(
            DeckService(
                deckRepository,
                statsRepository,
                CardService(MockCardRepo(), MockLegalityRepo()),
                MockSecurityService()
            ), MockSecurityService()
        )

    @Test
    fun testListDecks() {
        assertTrue(statsController.listDecks().isEmpty())
        deckRepository.save(Deck(1, "Deck1", "test"))
        assertEquals(1, statsController.listDecks().size)
        deckRepository.save(Deck(2, "Deck2", "test"))
        assertEquals(2, statsController.listDecks().size)
    }

    @Test
    fun testBuildDeckList() {
        assertEquals(emptyList<Deck>(), statsController.buildDeckList(emptyList(), emptyList()))
        assertEquals(listOf(Deck(-1, "Deck1", "test")), statsController.buildDeckList(listOf("Deck1"), emptyList()))
        assertEquals(
            listOf(Deck(-1, "Deck1", "test"), Deck(-1, "Deck2", "test")),
            statsController.buildDeckList(listOf("Deck1", "Deck2"), emptyList())
        )
        assertEquals(
            listOf(Deck(-1, "Deck1", "test"), Deck(1, "Deck3", "test")),
            statsController.buildDeckList(listOf("Deck1", "Deck3"), listOf(Deck(1, "Deck3", "test")))
        )
    }

    @Test
    fun testAddScore() {
        statsController.addScore(emptyList())
        assertEquals(emptyList<Deck>(), deckRepository.findAll())
        statsController.addScore(listOf(DeckScoreModel("Deck1", 0)))
        assertEquals(listOf(Deck(1, "Deck1", "test")), deckRepository.findAll())
        statsController.addScore(listOf(DeckScoreModel("Deck1", 0), DeckScoreModel("Deck2", 0)))
        assertEquals(listOf(Deck(1, "Deck1", "test"), Deck(2, "Deck2", "test")), deckRepository.findAll())
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1", "test"), 0, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2", "test"), 0, 0, 0))),
            deckRepository.findAll().toList()[1].stats
        )
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1", "test"), 1, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2", "test"), 0, 1, 0))),
            deckRepository.findAll().toList()[1].stats
        )
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(
            mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, Deck(1, "Deck1", "test"), 2, 0, 0))),
            deckRepository.findAll().toList()[0].stats
        )
        assertEquals(
            mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, Deck(2, "Deck2", "test"), 0, 2, 0))),
            deckRepository.findAll().toList()[1].stats
        )
    }

    @Test
    fun accumulateStatsTest() {
        assertEquals(Deck.StatsAgainst(0, Deck(-1, "", "test"), 0, 0, 0), statsController.accumulateStats(emptyMap()))
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, "", "test"), 0, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, Deck(-1, "", "test"), 0, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, "", "test"), 1, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, Deck(-1, "", "test"), 1, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, Deck(-1, "", "test"), 1, 2, 0),
            statsController.accumulateStats(
                mapOf(
                    Pair(0, Deck.StatsAgainst(0, Deck(-1, "", "test"), 1, 0, 0)),
                    Pair(1, Deck.StatsAgainst(0, Deck(-1, "", "test"), 0, 2, 0))
                )
            )
        )
    }
}