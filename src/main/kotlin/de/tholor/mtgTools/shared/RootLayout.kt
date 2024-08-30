package de.tholor.mtgTools.shared

import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import de.tholor.mtgTools.shared.security.SecurityService

open class RootLayout(securityService: SecurityService) : HorizontalLayout() {
    private val navLayout = NavigationLayout(securityService)

    init {
        navLayout.maxWidth = "100px"
        addComponentAsFirst(navLayout)
    }
}