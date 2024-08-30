package de.tholor.mtgTools.pages.home.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import de.tholor.mtgTools.pages.home.controllers.IHomeController
import de.tholor.mtgTools.shared.RootLayout
import de.tholor.mtgTools.shared.security.SecurityService
import jakarta.annotation.security.PermitAll
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "")
@PageTitle("Mtg tools - Home")
@PermitAll
class HomeView @Autowired internal constructor(
    private val homeController: IHomeController,
    private val securityService: SecurityService
) : RootLayout(securityService) {
    private val searchStringBox = TextField()
    private val getDataButton = Button("Get Data")
    private val content = VerticalLayout(searchStringBox, getDataButton)

    init {
        searchStringBox.label = "Input string for Scryfall"
        getDataButton.addClickListener {
            homeController.writeRequestToDatabase(searchStringBox.value)
        }
        add(content)
    }
}