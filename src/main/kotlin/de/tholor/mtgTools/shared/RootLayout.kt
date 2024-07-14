package de.tholor.mtgTools.shared

import com.vaadin.flow.component.orderedlayout.HorizontalLayout

open class RootLayout : HorizontalLayout() {
    private val navLayout = NavigationLayout()

    init {
        navLayout.maxWidth = "100px"
        addComponentAsFirst(navLayout)
    }
}