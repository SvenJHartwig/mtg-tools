package de.tholor.web.model.services

import de.tholor.web.model.Deck
import de.tholor.web.model.repositories.DeckRepository
import de.tholor.web.model.repositories.StatsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeckService @Autowired internal constructor(
    private val deckRepository: DeckRepository,
    private val statsRepository: StatsRepository
) : IDeckService {

    override fun listDecks(): List<Deck> {
        return deckRepository.findAll().toList()
    }

    override fun save(vararg decks: Deck): MutableIterable<Deck> {
        val deckList = decks.toList()
        deckList.parallelStream().forEach { deck ->
            deck.stats.forEach {
                val savedStat = statsRepository.save(it.value)
                deck.stats[it.key] = savedStat
            }
        }
        return deckRepository.saveAll(deckList)
    }

    override fun findDeckNameByID(id: Long): String {
        val deck = deckRepository.findById(id)
        if (deck.isPresent)
            return deck.get().name
        return ""
    }

    override fun deleteDeck(deck: Deck) {
        val otherDeckIds = mutableSetOf<Long>()
        deck.stats.forEach {
            otherDeckIds.add(it.key)
        }
        val otherDecks = deckRepository.findAllById(otherDeckIds)
        val otherDeckStats = otherDecks.map {
            it.stats[deck.id]
        }.toList()
        statsRepository.deleteAll(otherDeckStats)
        deckRepository.delete(deck)
    }

    fun listDecksWithNames(names: List<String>): List<Deck> {
        return deckRepository.findAllByNameIn(names).toList()
    }
}