package de.tholor.mtgTools.mockComponents

import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.model.repositories.DeckRepository
import io.ktor.util.reflect.*
import java.util.*

class MockDeckRepo : DeckRepository {
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