package de.tholor.mtgTools.pages.decks.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.Deck
import de.tholor.mtgTools.pages.decks.controllers.CardGridRow
import de.tholor.mtgTools.pages.decks.controllers.IDecksController
import de.tholor.mtgTools.shared.RootLayout
import de.tholor.mtgTools.shared.security.SecurityService
import jakarta.annotation.security.PermitAll
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "decks")
@PageTitle("Mtg tools - Decks")
@PermitAll
class DecksView @Autowired internal constructor(
    private val decksController: IDecksController,
    private val securityService: SecurityService
) : RootLayout(securityService),
    BeforeEnterObserver {
    private val deckGrid = Grid<Deck>()
    private val addDeckText = TextField()
    private val addDeckButton = Button("Add deck")
    private val addDeckLayout = HorizontalLayout(addDeckText, addDeckButton)
    private val deckLayout = VerticalLayout(deckGrid, addDeckLayout)
    private val cardGrid = Grid<CardGridRow>()
    private val addCardText = ComboBox<Card>()
    private val addCardButton = Button("Add card")
    private val addCardLayout = HorizontalLayout(addCardText, addCardButton)
    private val standardLegalLabel = Div("")
    private val cardLayout = VerticalLayout(cardGrid, addCardLayout, standardLegalLabel)
    private val content = HorizontalLayout(deckLayout, cardLayout)
    private var selectedDeck: Deck? = null

    init {
        deckGrid.addColumn { deck -> deck.name }.setHeader("Deck")
        deckGrid.addItemClickListener {
            selectedDeck = it.item
            updateValues()
        }
        cardGrid.addColumn { row -> "${row.card.name} x${row.number}" }.setHeader("Card name").setSortable(true)
        cardGrid.addComponentColumn { row ->
            val button = Button("Delete")
            button.addClickListener {
                decksController.deleteCardFromDeck(selectedDeck!!, row.card)
                updateValues()
            }
            button
        }
        deckGrid.minWidth = "40%"
        content.minWidth = "80%"
        addDeckButton.addClickListener {
            decksController.addDeck(addDeckText.value)
            updateValues()
        }
        addCardText.setItems(decksController.listCards())
        addCardText.setItemLabelGenerator { card -> card.name }
        cardLayout.isVisible = false
        cardLayout.isPadding = false
        deckLayout.isPadding = false
        content.isMargin = true
        addCardButton.addClickListener {
            if (addCardText.value != null && selectedDeck != null) {
                decksController.addCardToDeck(selectedDeck!!, addCardText.value)
                updateValues()
            }
        }
        add(content)
    }

    private fun updateValues() {
        deckGrid.setItems(decksController.listDecks())
        if (selectedDeck != null) {
            cardLayout.isVisible = true
            val cardGridMap = decksController.buildCardRowMap(selectedDeck!!)
            cardGrid.setItems(cardGridMap.values)
            standardLegalLabel.text = "Standard legal: " + decksController.isStandardLegal(selectedDeck!!)
        }
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        updateValues()
    }
}
