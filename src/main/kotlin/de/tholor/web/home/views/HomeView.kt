package de.tholor.web.home.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.router.Route
import de.tholor.web.home.controllers.HomeController
import de.tholor.web.shared.RootLayout


@Route("")
class HomeView internal constructor(homeController: HomeController) : RootLayout() {
    private val getDataButton = Button("Get Data")

    init {
        getDataButton.addClickListener {
            homeController.getData()
        }
        super.add(getDataButton)
    }
}