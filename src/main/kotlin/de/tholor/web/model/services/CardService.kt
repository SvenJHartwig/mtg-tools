package de.tholor.web.model.services

import de.tholor.web.model.CardList
import de.tholor.web.model.repositories.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService @Autowired internal constructor(
    private val cardRepository: CardRepository
) : ICardService {

    override fun save(cards: CardList) {
        cardRepository.saveAll(cards.data)
    }
}