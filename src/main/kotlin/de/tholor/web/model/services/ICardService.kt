package de.tholor.web.model.services

import de.tholor.web.model.CardList
import org.springframework.stereotype.Service

@Service
interface ICardService {
    fun save(cards: CardList)
}