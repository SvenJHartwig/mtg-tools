package de.tholor.web.pages.stats.components

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import de.tholor.web.model.Deck
import de.tholor.web.pages.stats.controllers.IStatsController

class DeckDetailsDialog(val deck: Deck, private val statsController: IStatsController) : Dialog() {
    private val statsGrid = Grid<Pair<Long, Deck.StatsAgainst>>()

    init {
        statsGrid.setItems(deck.stats.map { entry -> Pair(entry.key, entry.value) }.toList())
        statsGrid.addColumn { stats ->
            statsController.findDeckNameByID(stats.first)
        }.setHeader("Against deck").setAutoWidth(true)
        statsGrid.addColumn { stats ->
            stats.second.wins
        }.setHeader("Wins")
        statsGrid.addColumn { stats ->
            stats.second.losses
        }.setHeader("Losses")
        statsGrid.addColumn { stats ->
            stats.second.draws
        }.setHeader("Draws")

        add(statsGrid)
        val cancelButton = Button("Cancel") { _ -> close() }
        footer.add(cancelButton)
        minWidth = "500px"
    }
}