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
        deckScore.allowedCharPattern = "[0-9*]"
        add(deckName, deckScore)
    }

    fun getName(): String {
        return deckName.value
    }

    fun getScore(): Double {
        return deckScore.value
    }
}