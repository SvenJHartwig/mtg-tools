<h1>MTG tools</h1>

A simple project for organizing a mtg collection and tracking the wins and losses of decks against each other.

<h3>Usage</h3>
<h4>Home page</h4>
Mtg card data ist taken from [Scryfall](https://scryfall.com/). On the Home page, you can enter a query into the text
box and click on the button to query Scryfall and write the answer into the database.
<h4>Stats page</h4>
On the stats page, the wins and losses of the decks against each other are tracked. For each deck in a match, you can
enter the name and how many games it won in the match below. Clicking on the <code>Add result</code> button writes the
result into the database, and if the written deck didn't exist before, creates it in the database.<br>
Double-clicking on a deck in the statistics grid opens details for this deck's results.
<h4>Cards page</h4>
Provides data on all the cards currently in the database.
<h4>Decks page</h4>
On the decks page, you can manage your decks. With the <code>Add deck</code> button, you can add a deck without
associating stats to it to the database.
After clicking on a deck in the decks grid, the list of cards contained in the deck is shown. Here, cards can also be
added and removed from the deck. Only cards that are already in the database can be added or removed.
<h3>Building</h3>
This is a standard Spring application and can be run locally with gradle. A database adhering to the specifications in
application.properties must be provided.