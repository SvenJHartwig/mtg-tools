package de.tholor.mtgTools.pages.cards.controllers

import org.springframework.stereotype.Controller

@Controller
interface ICardsController {
    fun isStandardLegal(name: String, callback: (Boolean) -> (Unit))
}