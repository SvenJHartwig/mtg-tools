package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import de.tholor.web.pages.stats.components.DeckScore
import org.springframework.stereotype.Controller

@Controller
interface IStatsController {
    fun listDecks(): List<Deck>
    fun addScore(scores: List<DeckScore>)
    fun buildDeckList(allNames: List<String>, existingDecks: List<Deck>): List<Deck>
}