package de.tholor.web.mockComponents

import de.tholor.web.model.Deck
import de.tholor.web.model.repositories.StatsRepository
import java.util.*

class MockStatsRepo : StatsRepository {
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