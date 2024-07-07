package de.tholor.web.pages.cards.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import de.tholor.web.pages.cards.controllers.ICardsController
import de.tholor.web.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "cards")
class CardsView @Autowired internal constructor(
    private val cardsController: ICardsController
) : RootLayout() {
    private val searchStringBox = TextField()
    private val checkCardButton = Button("Check")
    private val answer = Div()
    private val content = VerticalLayout(searchStringBox, checkCardButton, answer)

    init {
        searchStringBox.label = "Is this card standard legal?"
        checkCardButton.addClickListener {
            cardsController.isStandardLegal(searchStringBox.value) { answer.text = it.toString() }
        }
        add(content)
    }
}