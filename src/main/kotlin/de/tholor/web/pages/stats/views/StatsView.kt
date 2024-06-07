package de.tholor.web.pages.stats.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterListener
import com.vaadin.flow.router.Route
import de.tholor.web.model.Deck
import de.tholor.web.pages.stats.components.DeckScore
import de.tholor.web.pages.stats.controllers.IStatsController
import de.tholor.web.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired

@Route("Stats")
class StatsView @Autowired internal constructor(private val statsController: IStatsController) : RootLayout(),
    BeforeEnterListener {
    private val statsGrid = Grid<Deck>(Deck::class.java)
    private val deckScores = listOf(DeckScore(1), DeckScore(2))
    private val decksLayout = HorizontalLayout(deckScores[0], deckScores[1])
    private val addScoreButton = Button("Ergebnis hinzufügen")

    init {
        statsGrid.setId("stats-view-stats-grid")
        addScoreButton.addClickListener { _ -> }
        add(statsGrid, decksLayout, addScoreButton)
    }

    private fun updateValues() {
        statsGrid.setItems(statsController.listDecks())
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        updateValues()
    }
}