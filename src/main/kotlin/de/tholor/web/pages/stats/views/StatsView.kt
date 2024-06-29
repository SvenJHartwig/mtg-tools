package de.tholor.web.pages.stats.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route
import de.tholor.web.model.Deck
import de.tholor.web.pages.stats.components.DeckDetailsDialog
import de.tholor.web.pages.stats.components.DeckScore
import de.tholor.web.pages.stats.controllers.IStatsController
import de.tholor.web.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired

@Route("Stats")
class StatsView @Autowired internal constructor(private val statsController: IStatsController) : RootLayout(),
    BeforeEnterObserver {
    private val statsGrid = Grid<Deck>()
    private val deckScores = listOf(DeckScore(1), DeckScore(2))
    private val decksLayout = HorizontalLayout(deckScores[0], deckScores[1])
    private val addScoreButton = Button("Ergebnis hinzufügen")

    init {
        statsGrid.setId("stats-view-stats-grid")
        statsGrid.addColumn { deck -> deck.name }.setHeader("Deck")
        statsGrid.addColumn { deck ->
            statsController.accumulateStats(deck.stats).wins
        }.setHeader("Wins")
        statsGrid.addColumn { deck ->
            statsController.accumulateStats(deck.stats).losses
        }.setHeader("Losses")
        statsGrid.addColumn { deck ->
            statsController.accumulateStats(deck.stats).draws
        }.setHeader("Draws")
        statsGrid.addItemDoubleClickListener { itemDoubleClickEvent -> DeckDetailsDialog(itemDoubleClickEvent.item).open() }
        addScoreButton.addClickListener { _ ->
            statsController.addScore(deckScores.map { deckScore -> deckScore.model })
            updateValues()
        }
        add(statsGrid, decksLayout, addScoreButton)
    }

    private fun updateValues() {
        statsGrid.setItems(statsController.listDecks())
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        updateValues()
    }
}