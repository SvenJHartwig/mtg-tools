package de.tholor.web.model

import kotlinx.serialization.Serializable

/**
 * Helper class to decode the json response from scryfall
 */
@Serializable
data class CardList(
    val `data`: List<Card>,
    val has_more: Boolean,
    val `object`: String,
    val total_cards: Int
)