package de.tholor.mtgTools.pages.stats.components

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.pages.stats.controllers.IStatsController

class DeckDetailsDialog(val deck: Deck, private val statsController: IStatsController, val onDelete: () -> Unit) :
    Dialog() {
    private val statsGrid = Grid<Pair<Long, Deck.StatsAgainst>>()
    private val deleteDeckButton = Button("Delete deck")

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

        deleteDeckButton.addClickListener {
            statsController.deleteDeck(deck)
            onDelete()
            close()
        }

        add(statsGrid, deleteDeckButton)
        val cancelButton = Button("Cancel") { _ -> close() }
        footer.add(cancelButton)
        minWidth = "500px"
    }
}