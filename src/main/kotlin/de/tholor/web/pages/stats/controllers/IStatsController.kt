package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import org.springframework.stereotype.Controller

@Controller
interface IStatsController {
    public fun listDecks(): List<Deck>
}