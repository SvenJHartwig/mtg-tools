package de.tholor.mtgTools.model.services

import de.tholor.mtgTools.model.Card
import de.tholor.mtgTools.model.CardList
import org.springframework.stereotype.Service

@Service
interface ICardService {
    fun save(cards: CardList)

    /**
     * Checks if the card with the given name is currently legal in standard. If there is no card with the given name in
     * the database, scryfall is requested for the name. If scryfall answers that the card exists, it is written into the database.
     */
    fun isStandardLegal(name: String): Boolean

    /**
     * Executes a scryfall lookup with the given search string.
     */
    fun writeRequestToDatabase(s: String)
    fun listCards(): List<Card>
}