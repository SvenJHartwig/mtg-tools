package de.tholor.mtgTools.mockComponents

import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.Legalities
import de.tholor.mtgTools.model.repositories.CardRepository
import io.ktor.util.reflect.*
import java.util.*

class MockCardRepo : CardRepository {
    val cardList = mutableListOf<Card>()
    override fun existsByName(name: String): Boolean {
        return name == "Island" || name == "Sheoldred" || name == "Dwarven soldier" || cardList.size > 0 && name == "Resolute Reinforcements"
    }

    override fun findByName(name: String): Card {
        if (name == "Sheoldred") {
            return Card(cardId = 1, name = "Sheoldred", legalities = Legalities(standard = "legal"))
        }
        if (name == "Dwarven soldier") {
            return Card(cardId = 2, name = "Dwarven soldier", legalities = Legalities(standard = "not_legal"))
        }
        if (cardList.size > 0) {
            return Card(cardId = 3, name = "Resolute Reinforcements", legalities = Legalities(standard = "legal"))
        }
        if (name == "Island") {
            return Card(cardId = 4, name = "Island", legalities = Legalities(standard = "legal"))
        }
        return Card()
    }

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