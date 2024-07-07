package de.tholor.web.pages.cards.controllers

import de.tholor.web.model.services.ICardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CardsController @Autowired internal constructor(
    private val cardService: ICardService
) : ICardsController {
    override fun isStandardLegal(name: String, callback: (Boolean) -> (Unit)) {
        callback.invoke(cardService.isStandardLegal(name))
    }
}