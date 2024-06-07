package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import de.tholor.web.model.services.DeckService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class StatsController @Autowired internal constructor(val deckService: DeckService) : IStatsController {
    override fun listDecks(): List<Deck> {
        return deckService.listDecks()
    }

}