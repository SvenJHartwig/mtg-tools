package de.tholor.web.model.services

import de.tholor.web.model.Card
import de.tholor.web.model.CardList
import de.tholor.web.model.repositories.CardRepository
import de.tholor.web.model.repositories.LegalityRepository
import de.tholor.web.shared.logger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CardService @Autowired internal constructor(
    private val cardRepository: CardRepository,
    private val legalityRepository: LegalityRepository
) : ICardService {

    val client = HttpClient(CIO)
    private val log by logger()
    private val json = Json { coerceInputValues = true; ignoreUnknownKeys = true }


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
        } else {
            writeRequestToDatabase("name%3A$name")
            if (cardRepository.existsByName(name)) {
                return cardRepository.findByName(name).legalities.standard == "legal"
            }
            return false
        }
    }

    override fun writeRequestToDatabase(s: String) {
        if (s.isEmpty()) {
            return
        }
        val response: HttpResponse
        runBlocking {
            response = client.request("https://api.scryfall.com/cards/search?q=$s") {
                method = HttpMethod.Get
                headers {
                    append(HttpHeaders.Accept, "text/json")
                }
            }
            if (response.status.isSuccess()) {
                val cards = json.decodeFromString<CardList>(response.bodyAsText())
                save(cards)
            }
        }
    }

    override fun listCards(): List<Card> {
        return cardRepository.findAll().toList()
    }
}