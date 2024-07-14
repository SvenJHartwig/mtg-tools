package de.tholor.mtgTools.pages.home.controllers

import org.springframework.stereotype.Controller

@Controller
interface IHomeController {
    fun writeRequestToDatabase(s: String)
}