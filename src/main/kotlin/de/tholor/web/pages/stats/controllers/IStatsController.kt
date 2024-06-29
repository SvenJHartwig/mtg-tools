package de.tholor.web.pages.stats.controllers

import de.tholor.web.model.Deck
import de.tholor.web.pages.stats.components.DeckScoreModel
import org.springframework.stereotype.Controller

@Controller
interface IStatsController {
    fun listDecks(): List<Deck>
    fun addScore(scores: List<DeckScoreModel>)
    fun buildDeckList(allNames: List<String>, existingDecks: List<Deck>): List<Deck>
    fun accumulateStats(stats: Map<Long, Deck.StatsAgainst>): Deck.StatsAgainst
    fun findDeckNameByID(id: Long): String
}