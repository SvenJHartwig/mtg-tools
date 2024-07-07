package de.tholor.web.cards

import de.tholor.web.mockComponents.MockCardRepo
import de.tholor.web.mockComponents.MockLegalityRepo
import de.tholor.web.model.services.CardService
import de.tholor.web.pages.cards.controllers.CardsController
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardsTests {
    private val cardsController = CardsController(CardService(MockCardRepo(), MockLegalityRepo()))

    @Test
    fun testIsStandardLegal() {
        var result = false
        cardsController.isStandardLegal("") { result = it }
        assertFalse(result)
        cardsController.isStandardLegal("Sheoldred") { result = it }
        assertTrue(result)
        cardsController.isStandardLegal("Dwarven soldier") { result = it }
        assertFalse(result)
        cardsController.isStandardLegal("Island") { result = it }
        assertTrue(result)
    }
}