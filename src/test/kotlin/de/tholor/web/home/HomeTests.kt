package de.tholor.web.home

import de.tholor.web.mockComponents.MockCardRepo
import de.tholor.web.model.Card
import de.tholor.web.model.services.CardService
import de.tholor.web.pages.home.controllers.HomeController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.system.OutputCaptureExtension

@ExtendWith(OutputCaptureExtension::class)
class HomeTests {
    private val mockCardRepo = MockCardRepo()
    private val homeController = HomeController(CardService(mockCardRepo))

    @Test
    fun testWriteRequestToDatabase() {
        homeController.writeRequestToDatabase("")
        assertEquals(0, mockCardRepo.count())
        homeController.writeRequestToDatabase("name%3Adwarven soldier+c%3Ar")
        assertEquals(Card(cardId = 1, name = "dwarven soldier"), mockCardRepo.findById(1).get())
    }
}