package de.tholor.web.pages.stats.components

class DeckScoreModel {
    var deckName = ""
    var deckScore = 0

    constructor()
    constructor(deckName: String, deckScore: Int) {
        this.deckName = deckName
        this.deckScore = deckScore
    }
}