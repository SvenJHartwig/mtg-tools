package de.tholor.web.pages.home.controllers

import de.tholor.web.model.services.ICardService
import de.tholor.web.shared.logger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class HomeController @Autowired internal constructor(
    private val cardService: ICardService
) : IHomeController {

    val client = HttpClient(CIO)
    private val log by logger()

    override fun getData() {
        log.info("In logger")
        val response: HttpResponse
        runBlocking {
            response = client.request("https://api.scryfall.com/cards/search?q=c%3Awhite+mv%3D1") {
                method = HttpMethod.Get
                headers {
                    append(HttpHeaders.Accept, "text/json")
                }
            }
            log.info("Response Code: " + response.status.value)
            //    log.info(response.bodyAsText())
        }
        log.info(cardService.createNewCard())
    }


}