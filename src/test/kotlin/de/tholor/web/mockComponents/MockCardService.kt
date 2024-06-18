package de.tholor.web.mockComponents

import de.tholor.web.model.services.ICardService

class MockCardService : ICardService {
    override fun createNewCard(): Boolean {
        return true
    }
}