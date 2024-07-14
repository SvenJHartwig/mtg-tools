package de.tholor.mtgTools.model.services

import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.model.repositories.DeckRepository
import de.tholor.mtgTools.model.repositories.StatsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeckService @Autowired internal constructor(
    private val deckRepository: DeckRepository,
    private val statsRepository: StatsRepository,
    private val cardService: CardService
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

    override fun isStandardLegal(deck: Deck): Boolean {
        if (deck.cardList.size < 60)
            return false
        var foundNonLegal: Boolean
        val cardsMap = mutableMapOf<String, Int>()
        deck.cardList.forEach {
            if (cardsMap[it.name] == null) {
                cardsMap[it.name] = 1
            } else {
                cardsMap[it.name] = cardsMap[it.name]!! + 1
            }
            foundNonLegal = !cardService.isStandardLegal(it.name)
            if (foundNonLegal) {
                return false
            }
        }
        return !cardsMap.any {
            !listOf(
                "Island",
                "Plains",
                "Mountain",
                "Swamp",
                "Forest",
                "Snow-Covered Island",
                "Snow-Covered Plains",
                "Snow-Covered Mountain",
                "Snow-Covered Swamp",
                "Snow-Covered Forest",
                "Wastes",
                "Snow-Covered Wastes"
            ).contains(it.key) && it.value > 4
        }
    }

    fun listDecksWithNames(names: List<String>): List<Deck> {
        return deckRepository.findAllByNameIn(names).toList()
    }
}