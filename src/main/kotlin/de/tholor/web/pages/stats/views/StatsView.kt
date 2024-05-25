package de.tholor.web.pages.stats.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.Route
import de.tholor.web.pages.stats.components.DeckScore
import de.tholor.web.shared.RootLayout

@Route("Stats")
class StatsView : RootLayout() {
    private val statsGrid = Grid<String>()
    private val deckScores = listOf(DeckScore(1), DeckScore(2))
    private val decksLayout = HorizontalLayout(deckScores[0], deckScores[1])
    private val addScoreButton = Button("Ergebnis hinzufügen")

    init {
        addScoreButton.addClickListener { _ -> }
        super.add(statsGrid, decksLayout, addScoreButton)
    }
}