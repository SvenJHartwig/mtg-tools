package de.tholor.mtgTools.shared

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.RouterLink
import de.tholor.mtgTools.pages.cards.views.CardsView
import de.tholor.mtgTools.pages.decks.views.DecksView
import de.tholor.mtgTools.pages.home.views.HomeView
import de.tholor.mtgTools.pages.stats.views.StatsView
import de.tholor.mtgTools.shared.security.SecurityService

class NavigationLayout(private val securityService: SecurityService) : VerticalLayout() {
    private val homeLink = RouterLink("Home", HomeView::class.java)
    private val statsLink = RouterLink("Stats", StatsView::class.java)
    private val cardsLink = RouterLink("Cards", CardsView::class.java)
    private val decksLink = RouterLink("Decks", DecksView::class.java)

    init {
        val logout: Button = Button("Logout") { securityService.logout() }
        add(homeLink, statsLink, cardsLink, decksLink, logout)
    }

}