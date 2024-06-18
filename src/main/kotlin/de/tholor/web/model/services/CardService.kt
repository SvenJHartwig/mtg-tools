package de.tholor.web.model.services

import de.tholor.web.model.Card
import de.tholor.web.model.repositories.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService @Autowired internal constructor(
    private val cardRepository: CardRepository
) : ICardService {

    override fun createNewCard(): Boolean {
        val newCard = Card(1L, "Test")
        cardRepository.save(newCard)
        return cardRepository.existsById(1)
    }
}