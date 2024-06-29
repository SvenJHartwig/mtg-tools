package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.stats.components.DeckScoreModel
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class StatsController @Autowired internal constructor(val deckService: DeckService) : IStatsController {
    override fun listDecks(): List<Deck> {
        return deckService.listDecks()
    }

    override fun addScore(scores: List<DeckScoreModel>) {
        val scoresMap = mutableMapOf<String, Int>()
        for (score: DeckScoreModel in scores) {
            scoresMap[score.deckName] = score.deckScore
        }
        val deckNameList = scoresMap.keys.toList()
        var deckList = buildDeckList(deckNameList, deckService.listDecksWithNames(deckNameList))

        deckList = deckService.save(*deckList.toTypedArray()).toList()

        deckList.forEach { deck ->
            deckList.forEach { deck2 ->
                if (deck2 != deck) {
                    if (deck.stats[deck2.id] == null) {
                        deck.stats[deck2.id] = Deck.StatsAgainst(-1, scoresMap[deck.name] ?: 0, 0, 0)
                    } else {
                        deck.stats[deck2.id]!!.wins += scoresMap[deck.name] ?: 0
                    }
                    if (deck2.stats[deck.id] == null) {
                        deck2.stats[deck.id] = Deck.StatsAgainst(-1, 0, scoresMap[deck.name] ?: 0, 0)
                    } else {
                        deck2.stats[deck.id]!!.losses += scoresMap[deck.name] ?: 0
                    }
                }
            }
        }
        runBlocking {
            deckService.save(*deckList.toTypedArray())
        }
    }

    override fun buildDeckList(allNames: List<String>, existingDecks: List<Deck>): List<Deck> {
        val result = allNames
            .parallelStream()
            .map { name ->
                existingDecks.find { deck: Deck -> deck.name == name } ?: Deck(-1, name)
            }
            .toList()
        return result
    }

    override fun accumulateStats(stats: Map<Long, Deck.StatsAgainst>): Deck.StatsAgainst {
        var wins = 0
        var losses = 0
        var draws = 0
        stats.forEach { entry ->
            wins += entry.value.wins
            losses += entry.value.losses
            draws += entry.value.draws
        }
        return Deck.StatsAgainst(0, wins, losses, draws)
    }

}