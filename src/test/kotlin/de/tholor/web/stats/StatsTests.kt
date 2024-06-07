package de.tholor.web.stats

import de.tholor.web.model.Deck
import de.tholor.web.model.repositories.DeckRepository
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.stats.controllers.StatsController
import io.ktor.util.reflect.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class StatsTests() {
    class DeckRepoImpl : DeckRepository {
        val deckList = mutableListOf<Deck>()
        override fun <S : Deck?> save(entity: S & Any): S & Any {
            if (entity.instanceOf(Deck::class)) {
                deckList.add(entity)
            }
            return entity
        }

        override fun <S : Deck?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
            TODO("Not yet implemented")
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

    private val deckRepository = DeckRepoImpl()
    private val statsController = StatsController(DeckService(deckRepository))

    @Test
    fun testListDecks() {
        assertTrue(statsController.listDecks().isEmpty())
        deckRepository.save(Deck(1, "Deck1"))
        assertEquals(1, statsController.listDecks().size)
        deckRepository.save(Deck(2, "Deck2"))
        assertEquals(2, statsController.listDecks().size)
    }
}