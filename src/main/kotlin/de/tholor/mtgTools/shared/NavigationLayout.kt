package de.tholor.mtgTools.shared

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.RouterLink
import de.tholor.mtgTools.pages.cards.views.CardsView
import de.tholor.mtgTools.pages.decks.views.DecksView
import de.tholor.mtgTools.pages.home.views.HomeView
import de.tholor.mtgTools.pages.stats.views.StatsView

class NavigationLayout : VerticalLayout() {
    private val homeLink = RouterLink("Home", HomeView::class.java)
    private val statsLink = RouterLink("Stats", StatsView::class.java)
    private val cardsLink = RouterLink("Cards", CardsView::class.java)
    private val decksLink = RouterLink("Decks", DecksView::class.java)

    init {
        add(homeLink, statsLink, cardsLink, decksLink)
    }

}