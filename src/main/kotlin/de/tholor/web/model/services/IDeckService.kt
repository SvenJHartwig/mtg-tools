package de.tholor.web.model.services

import de.tholor.web.model.Deck
import org.springframework.stereotype.Service

@Service
interface IDeckService {
    public fun listDecks(): List<Deck>
}