package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import de.tholor.web.model.services.DeckService
import de.tholor.web.pages.stats.components.DeckScore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class StatsController @Autowired internal constructor(val deckService: DeckService) : IStatsController {
    override fun listDecks(): List<Deck> {
        return deckService.listDecks()
    }

    override fun addScore(scores: List<DeckScore>) {
        val deckNameList = scores.map { deckScore -> deckScore.getName() }
        val deckList = buildDeckList(deckNameList, deckService.listDecksWithNames(deckNameList))
    }

    override fun buildDeckList(allNames: List<String>, existingDecks: List<Deck>): List<Deck> {
        val result = allNames.map { name ->
            existingDecks.find { deck: Deck -> deck.name == name } ?: Deck(-1, name)
        }
        return result
    }

}