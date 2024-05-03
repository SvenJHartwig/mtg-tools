package de.tholor.web.home.controllers

import de.tholor.web.shared.logger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import org.springframework.stereotype.Controller

@Controller
class HomeController {

    val client = HttpClient(CIO)
    private val log by logger()

    fun getData() {
        log.info { "In logger" }
    }


}