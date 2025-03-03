package de.tholor.mtgTools.cards

import de.tholor.mtgTools.mockComponents.MockCardRepo
import de.tholor.mtgTools.mockComponents.MockLegalityRepo
import de.tholor.mtgTools.model.services.CardService
import de.tholor.mtgTools.pages.cards.controllers.CardsController
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardsTests {
    private val cardsController = CardsController(CardService(MockCardRepo(), MockLegalityRepo()))

    /**
     * Sheoldred is contained in db and legal
     * Dwarven soldier is contained in db and not legal
     * Resolute Reinforcements is not contained in db and legal
     * Not existing Card fails to search
     */
    @Test
    fun testIsStandardLegal() {
        var result = false
        cardsController.isStandardLegal("") { result = it }
        assertFalse(result)
        cardsController.isStandardLegal("Sheoldred") { result = it }
        assertTrue(result)
        cardsController.isStandardLegal("Dwarven soldier") { result = it }
        assertFalse(result)
        cardsController.isStandardLegal("Resolute Reinforcements") { result = it }
        assertTrue(result)
        cardsController.isStandardLegal("Not existing Card") { result = it }
        assertFalse(result)
    }
}