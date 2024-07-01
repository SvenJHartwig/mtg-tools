package de.tholor.web.model

import io.ktor.util.reflect.*
import jakarta.persistence.*
import java.io.Serializable

@Entity
class Deck(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String
) : Serializable {
    @ManyToMany
    private lateinit var cardList: List<Card>

    @Entity
    data class StatsAgainst(
        @Id @GeneratedValue val id: Long,
        @ManyToOne val deck: Deck,
        var wins: Int,
        var losses: Int,
        var draws: Int
    ) :
        Serializable

    @ElementCollection
    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER, orphanRemoval = true)
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