package de.tholor.mtgTools.mockComponents

import de.tholor.mtgTools.model.Legalities
import de.tholor.mtgTools.model.repositories.LegalityRepository
import java.util.*

class MockLegalityRepo : LegalityRepository {
    override fun <S : Legalities?> save(entity: S & Any): S & Any {
        return entity
    }

    override fun <S : Legalities?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<Legalities> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<Legalities> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Legalities) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Legalities>) {
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

    override fun findById(id: Long): Optional<Legalities> {
        TODO("Not yet implemented")
    }
}