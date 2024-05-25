package de.tholor.web.pages.stats.components

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField

class DeckScore(number: Int) : VerticalLayout() {
    private val deckName = TextField()
    private val deckScore = NumberField()

    init {
        deckName.label = "Name $number. Deck"
        deckScore.label = "Siege $number. Deck"
        add(deckName, deckScore)
    }
}