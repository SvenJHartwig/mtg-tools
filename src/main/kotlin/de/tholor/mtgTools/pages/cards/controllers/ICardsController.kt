package de.tholor.mtgTools.pages.cards.controllers

import de.tholor.mtgTools.model.Card
import org.springframework.stereotype.Controller

@Controller
interface ICardsController {
    fun isStandardLegal(name: String, callback: (Boolean) -> (Unit))
    fun listCards(): List<Card>
}