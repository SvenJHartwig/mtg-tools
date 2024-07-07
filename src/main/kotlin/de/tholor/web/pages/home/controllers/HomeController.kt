package de.tholor.web.pages.home.controllers

import de.tholor.web.model.CardList
import de.tholor.web.model.services.ICardService
import de.tholor.web.shared.logger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class HomeController @Autowired internal constructor(
    private val cardService: ICardService
) : IHomeController {

    val client = HttpClient(CIO)
    private val log by logger()
    private val json = Json { coerceInputValues = true; ignoreUnknownKeys = true }

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
                cardService.save(cards)
            }
        }
    }


}