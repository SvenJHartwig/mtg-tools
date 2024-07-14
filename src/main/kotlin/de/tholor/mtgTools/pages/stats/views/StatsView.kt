package de.tholor.mtgTools.pages.stats.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.pages.stats.components.DeckDetailsDialog
import de.tholor.mtgTools.pages.stats.components.DeckScore
import de.tholor.mtgTools.pages.stats.controllers.IStatsController
import de.tholor.mtgTools.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired

@Route("Stats")
class StatsView @Autowired internal constructor(private val statsController: IStatsController) : RootLayout(),
    BeforeEnterObserver {
    private val statsGrid = Grid<Deck>()
    private val numberOfDecks = NumberField()
    private val changeDeckNumber = Button("Change number of decks")
    private val numberLayout = VerticalLayout(numberOfDecks, changeDeckNumber)
    private val deckScores = mutableListOf(DeckScore(1), DeckScore(2))
    private val addScoreButton = Button("Add result")
    private val deckScoreLayout = HorizontalLayout(deckScores[0], deckScores[1])
    private val decksLayout = HorizontalLayout(numberLayout, deckScoreLayout, addScoreButton)
    private val content = VerticalLayout(statsGrid, decksLayout)

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
        statsGrid.addItemDoubleClickListener { itemDoubleClickEvent ->
            DeckDetailsDialog(
                itemDoubleClickEvent.item,
                statsController
            ) { updateValues() }.open()
        }
        addScoreButton.addClickListener { _ ->
            statsController.addScore(deckScores.map { deckScore -> deckScore.model })
            updateValues()
        }
        changeDeckNumber.addClickListener {
            while (deckScores.size < numberOfDecks.value.toInt()) {
                deckScores.add(DeckScore(deckScores.size + 1))
                deckScoreLayout.add(deckScores.last())
            }
            while (deckScores.size > numberOfDecks.value.toInt()) {
                deckScoreLayout.remove(deckScores.last())
                deckScores.removeLast()
            }
        }
        add(content)
    }

    private fun updateValues() {
        statsGrid.setItems(statsController.listDecks())
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        updateValues()
    }
}