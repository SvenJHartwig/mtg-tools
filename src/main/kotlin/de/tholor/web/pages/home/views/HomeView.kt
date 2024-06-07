package de.tholor.web.pages.home.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.router.Route
import de.tholor.web.pages.home.controllers.IHomeController
import de.tholor.web.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired


@Route(value = "")
class HomeView @Autowired internal constructor(
    private val homeController: IHomeController
) : RootLayout() {
    private val getDataButton = Button("Get Data")

    init {
        getDataButton.addClickListener {
            homeController.getData()
        }
        add(getDataButton)
    }
}