package de.tholor.web.shared

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.RouterLink
import de.tholor.web.pages.home.views.HomeView
import de.tholor.web.pages.stats.views.StatsView

class NavigationLayout : VerticalLayout() {
    private val homeLink = RouterLink("Home", HomeView::class.java)
    private val statsLink = RouterLink("Stats", StatsView::class.java)

    init {
        add(homeLink, statsLink)
    }

    companion object {

    }
}