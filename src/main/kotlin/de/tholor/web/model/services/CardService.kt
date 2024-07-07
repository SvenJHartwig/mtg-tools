package de.tholor.web.model.services

import de.tholor.web.model.CardList
import de.tholor.web.model.repositories.CardRepository
import de.tholor.web.model.repositories.LegalityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService @Autowired internal constructor(
    private val cardRepository: CardRepository,
    private val legalityRepository: LegalityRepository
) : ICardService {

    override fun save(cards: CardList) {
        cards.data.forEach { card ->
            if (cardRepository.existsByName(card.name)) {
                card.cardId = cardRepository.findByName(card.name).cardId
            }
            card.legalities = legalityRepository.save(card.legalities)
        }
        cardRepository.saveAll(cards.data)
    }

    override fun isStandardLegal(name: String): Boolean {
        if (name.isEmpty()) {
            return false
        }
        if (cardRepository.existsByName(name)) {
            return cardRepository.findByName(name).legalities.standard == "legal"
        }
        return false
    }
}