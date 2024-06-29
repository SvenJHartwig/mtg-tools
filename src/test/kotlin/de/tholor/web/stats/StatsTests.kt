package de.tholor.web.stats

import de.tholor.web.model.Deck
import de.tholor.web.model.repositories.DeckRepository
import de.tholor.web.model.repositories.StatsRepository
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.stats.components.DeckScoreModel
import de.tholor.web.pages.stats.controllers.StatsController
import io.ktor.util.reflect.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class StatsTests {
    class DeckRepoImpl : DeckRepository {
        val deckList = mutableListOf<Deck>()
        override fun findAllByNameIn(name: List<String>): List<Deck> {
            return emptyList()
        }

        override fun <S : Deck?> save(entity: S & Any): S & Any {
            if (entity.instanceOf(Deck::class) && deckList.none { deck -> deck.name == entity.name }) {
                deckList.add(entity)
            }
            return entity
        }

        override fun <S : Deck?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
            entities.forEach { entity ->
                if (entity != null) {
                    if (entity.instanceOf(Deck::class) && deckList.none { deck -> deck.name == entity.name }) {
                        if (entity.id.toInt() == -1) {
                            deckList.add(Deck(deckList.size + 1.toLong(), entity.name))
                        } else {
                            deckList.add(entity)
                        }
                    }
                }
            }
            return deckList as MutableIterable<S>
        }

        override fun findAll(): MutableIterable<Deck> {
            return deckList
        }

        override fun findAllById(ids: MutableIterable<Long>): MutableIterable<Deck> {
            TODO("Not yet implemented")
        }

        override fun count(): Long {
            TODO("Not yet implemented")
        }

        override fun delete(entity: Deck) {
            TODO("Not yet implemented")
        }

        override fun deleteAllById(ids: MutableIterable<Long>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll(entities: MutableIterable<Deck>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll() {
            TODO("Not yet implemented")
        }

        override fun deleteById(id: Long) {
            TODO("Not yet implemented")
        }

        override fun existsById(id: Long): Boolean {
            TODO("Not yet implemented")
        }

        override fun findById(id: Long): Optional<Deck> {
            TODO("Not yet implemented")
        }

    }

    class StatsRepoImpl : StatsRepository {
        override fun <S : Deck.StatsAgainst?> save(entity: S & Any): S & Any {
            return entity
        }

        override fun <S : Deck.StatsAgainst?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
            TODO("Not yet implemented")
        }

        override fun findAll(): MutableIterable<Deck.StatsAgainst> {
            TODO("Not yet implemented")
        }

        override fun findAllById(ids: MutableIterable<Long>): MutableIterable<Deck.StatsAgainst> {
            TODO("Not yet implemented")
        }

        override fun count(): Long {
            TODO("Not yet implemented")
        }

        override fun delete(entity: Deck.StatsAgainst) {
            TODO("Not yet implemented")
        }

        override fun deleteAllById(ids: MutableIterable<Long>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll(entities: MutableIterable<Deck.StatsAgainst>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll() {
            TODO("Not yet implemented")
        }

        override fun deleteById(id: Long) {
            TODO("Not yet implemented")
        }

        override fun existsById(id: Long): Boolean {
            TODO("Not yet implemented")
        }

        override fun findById(id: Long): Optional<Deck.StatsAgainst> {
            TODO("Not yet implemented")
        }

    }

    private val deckRepository = DeckRepoImpl()
    private val statsRepository = StatsRepoImpl()
    private val statsController = StatsController(DeckService(deckRepository, statsRepository))

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
        assertEquals(mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, 0, 0, 0))), deckRepository.findAll().toList()[0].stats)
        assertEquals(mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, 0, 0, 0))), deckRepository.findAll().toList()[1].stats)
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, 1, 0, 0))), deckRepository.findAll().toList()[0].stats)
        assertEquals(mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, 0, 1, 0))), deckRepository.findAll().toList()[1].stats)
        statsController.addScore(listOf(DeckScoreModel("Deck1", 1), DeckScoreModel("Deck2", 0)))
        assertEquals(mutableMapOf(Pair(2L, Deck.StatsAgainst(-1, 2, 0, 0))), deckRepository.findAll().toList()[0].stats)
        assertEquals(mutableMapOf(Pair(1L, Deck.StatsAgainst(-1, 0, 2, 0))), deckRepository.findAll().toList()[1].stats)
    }

    @Test
    fun accumulateStatsTest() {
        assertEquals(Deck.StatsAgainst(0, 0, 0, 0), statsController.accumulateStats(emptyMap()))
        assertEquals(
            Deck.StatsAgainst(0, 0, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, 0, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, 1, 0, 0),
            statsController.accumulateStats(mapOf(Pair(0, Deck.StatsAgainst(0, 1, 0, 0))))
        )
        assertEquals(
            Deck.StatsAgainst(0, 1, 2, 0),
            statsController.accumulateStats(
                mapOf(
                    Pair(0, Deck.StatsAgainst(0, 1, 0, 0)),
                    Pair(1, Deck.StatsAgainst(0, 0, 2, 0))
                )
            )
        )
    }
}