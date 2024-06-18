package de.tholor.web.pages.stats.components

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField

class DeckScore(number: Int) : VerticalLayout() {
    private val deckName = TextField()
    private val deckScore = NumberField()
    val model = DeckScoreModel()

    init {
        deckName.label = "Name $number. Deck"
        deckScore.label = "Siege $number. Deck"
        deckScore.allowedCharPattern = "[0-9*]"
        deckName.addValueChangeListener { v -> model.deckName = v.value }
        deckScore.addValueChangeListener { v -> model.deckScore = v.value.toInt() }
        add(deckName, deckScore)
    }

    fun getName(): String {
        return model.deckName
    }

    fun getScore(): Int {
        return model.deckScore
    }
}