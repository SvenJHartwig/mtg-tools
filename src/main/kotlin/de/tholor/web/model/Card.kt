package de.tholor.web.model

import io.ktor.util.*
import jakarta.persistence.*
import kotlinx.serialization.Serializable

@Entity
@Table(name = "card")
@Serializable
data class Card(
    @Id
    @GeneratedValue
    var cardId: Long = -1,
    val artist: String = "",
    val artist_ids: List<String> = listOf(),
    val booster: Boolean = false,
    val border_color: String = "",
    val card_back_id: String = "",
    val cmc: Float = 0f,
    val collector_number: String = "",
    val color_identity: List<String> = listOf(),
    val colors: List<String> = listOf(),
    val digital: Boolean = false,
    val edhrec_rank: Int = 0,
    val finishes: List<String> = listOf(),
    val flavor_text: String = "",
    val foil: Boolean = false,
    val frame: String = "",
    val full_art: Boolean = false,
    val games: List<String> = listOf(),
    val highres_image: Boolean = false,
    val id: String = "",
    val illustration_id: String = "",
    val image_status: String = "",
//    @OneToOne
//    val image_uris: ImageUris = ImageUris(),
    val keywords: List<String> = listOf(),
    val lang: String = "",
    val layout: String = "",
    @OneToOne
    var legalities: Legalities = Legalities(),
    val mana_cost: String = "",
    val mtgo_foil_id: Int = 0,
    val mtgo_id: Int = 0,
    val multiverse_ids: List<Int> = listOf(),
    val name: String = "",
    val nonfoil: Boolean = false,
    val `object`: String = "",
    val oracle_id: String = "",
    val oracle_text: String = "",
    val oversized: Boolean = false,
    val power: String = "",
//    @OneToOne
//    val prices: Prices = Prices(),
    val prints_search_uri: String = "",
    val promo: Boolean = false,
//    @OneToOne
//    val purchase_uris: PurchaseUris = PurchaseUris(),
    val rarity: String = "",
    val released_at: String = "",
    val reprint: Boolean = false,
    val reserved: Boolean = false,
    val rulings_uri: String = "",
    val scryfall_set_uri: String = "",
    val scryfall_uri: String = "",
    //  val `set`: String = "",
    val set_id: String = "",
    val set_name: String = "",
    val set_search_uri: String = "",
    val set_type: String = "",
    val set_uri: String = "",
    val story_spotlight: Boolean = false,
    val textless: Boolean = false,
    val toughness: String = "",
    val type_line: String = "",
    val uri: String = "",
    val variation: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other::class != Card::class) {
            return false
        }
        val otherCard = other as Card
        return otherCard.cardId == cardId && otherCard.name.toLowerCasePreservingASCIIRules() == name.toLowerCasePreservingASCIIRules()
    }
}

@Entity
@Serializable
data class ImageUris(
    @Id
    @GeneratedValue
    val id: Long = -1,
    val art_crop: String = "",
    val border_crop: String = "",
    val large: String = "",
    val normal: String = "",
    val png: String = "",
    val small: String = ""
)

@Entity
@Serializable
data class Legalities(
    @Id
    @GeneratedValue
    val id: Long = -1,
    val alchemy: String = "",
    val brawl: String = "",
    val commander: String = "",
    val duel: String = "",
    val explorer: String = "",
    val future: String = "",
    val gladiator: String = "",
    val historic: String = "",
    val legacy: String = "",
    val modern: String = "",
    val oathbreaker: String = "",
    val oldschool: String = "",
    val pauper: String = "",
    val paupercommander: String = "",
    val penny: String = "",
    val pioneer: String = "",
    val predh: String = "",
    val premodern: String = "",
    val standard: String = "",
    val standardbrawl: String = "",
    val timeless: String = "",
    val vintage: String = ""
)

@Entity
@Serializable
data class Prices(
    @Id
    @GeneratedValue
    val id: Long = -1,
    val eur: String = "",
    val eur_foil: String = "",
    val tix: String = "",
    val usd: String = "",
    val usd_etched: String = "",
    val usd_foil: String = ""
)

@Entity
@Serializable
data class PurchaseUris(
    @Id
    @GeneratedValue
    val id: Long = -1,
    val cardhoarder: String = "",
    val cardmarket: String = "",
    val tcgplayer: String = ""
)