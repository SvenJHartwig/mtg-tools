package de.tholor.web.home.views

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.router.Route
import de.tholor.web.home.controllers.IHomeController
import de.tholor.web.shared.RootLayout
import org.springframework.beans.factory.annotation.Autowired


@Route("")
class HomeView : RootLayout() {
    private val getDataButton = Button("Get Data")
    private lateinit var homeController: IHomeController

    @Autowired
    internal fun HomeView(homeController: IHomeController) {
        this.homeController = homeController
    }

    init {
        getDataButton.addClickListener {
            homeController.getData()
        }
        super.add(getDataButton)
    }
}