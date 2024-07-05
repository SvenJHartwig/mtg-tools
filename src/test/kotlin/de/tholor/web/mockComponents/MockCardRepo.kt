package de.tholor.web.mockComponents

import de.tholor.web.model.Card
import de.tholor.web.model.repositories.CardRepository
import io.ktor.util.reflect.*
import java.util.*

class MockCardRepo : CardRepository {
    val cardList = mutableListOf<Card>()
    override fun <S : Card?> save(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Card?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach { entity ->
            if (entity != null) {
                if (entity.instanceOf(Card::class) && cardList.none { card -> card.name == entity.name }) {
                    if (entity.cardId.toInt() == -1) {
                        cardList.add(Card(cardId = cardList.size + 1.toLong(), name = entity.name))
                    } else {
                        cardList.add(entity)
                    }
                }
            }
        }
        return cardList as MutableIterable<S>
    }

    override fun findAll(): MutableIterable<Card> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<Card> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        return 0
    }

    override fun delete(entity: Card) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Card>) {
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

    override fun findById(id: Long): Optional<Card> {
        if (cardList.size >= id)
            return Optional.of(cardList[id.toInt() - 1])
        return Optional.empty()
    }
}