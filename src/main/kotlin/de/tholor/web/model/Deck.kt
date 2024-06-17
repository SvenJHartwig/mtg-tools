package de.tholor.web.model

import io.ktor.util.reflect.*
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Transient
import java.io.Serializable

@Entity
class Deck(
    @Id
    val id: Long,
    val name: String
) : Serializable {
    private lateinit var cardList: List<Card>

    data class StatsAgainst(var wins: Int, var losses: Int, var draws: Int) : Serializable {
    }

    @Transient
    val stats: MutableMap<Long, StatsAgainst> = mutableMapOf()

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (!other.instanceOf(Deck::class)) {
            return false
        }
        val otherDeck = other as Deck
        return otherDeck.id == this.id && other.name == this.name
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}