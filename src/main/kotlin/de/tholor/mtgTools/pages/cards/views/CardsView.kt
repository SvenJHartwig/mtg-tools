package de.tholor.mtgTools.pages.cards.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.pages.cards.controllers.ICardsController
import de.tholor.mtgTools.shared.RootLayout
import de.tholor.mtgTools.shared.security.SecurityService
import jakarta.annotation.security.PermitAll
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "cards")
@PageTitle("Mtg tools - Cards")
@PermitAll
class CardsView @Autowired internal constructor(
    private val cardsController: ICardsController,
    private val securityService: SecurityService
) : RootLayout(securityService) {
    private val cardsGrid = Grid<Card>()
    private val searchStringBox = TextField()
    private val checkCardButton = Button("Check")
    private val answer = Div()
    private val content = VerticalLayout(cardsGrid, searchStringBox, checkCardButton, answer)

    init {
        cardsGrid.setItems(cardsController.listCards())
        cardsGrid.addColumn { it.name }.setHeader("Name").setSortable(true)
        cardsGrid.addColumn { it.colors }.setHeader("Colors").setSortable(true)
        cardsGrid.addColumn { it.mana_cost }.setHeader("Mana cost")
        cardsGrid.addColumn { it.cmc }.setHeader("Mana value").setSortable(true)
        cardsGrid.addColumn { it.legalities.standard }.setHeader("Standard legality")
        searchStringBox.label = "Is this card standard legal?"
        checkCardButton.addClickListener {
            cardsController.isStandardLegal(searchStringBox.value) { answer.text = it.toString() }
        }
        add(content)
    }
}