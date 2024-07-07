package de.tholor.web.pages.home.controllers

import de.tholor.web.model.services.ICardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class HomeController @Autowired internal constructor(
    private val cardService: ICardService
) : IHomeController {


    override fun writeRequestToDatabase(s: String) {
        cardService.writeRequestToDatabase(s)
    }


}