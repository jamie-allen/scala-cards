package com.jamieallen.scalacards.war

import scala.collection.immutable.Queue

case class Card(id: Int, name: String)

case class Player(name: String, var cards: Queue[Card]) {
    var newCards = Queue[Card]()

    def getCard: Option[Card] = {
        var returnCard: Option[Card] = None

        // If the deck is empty, and they have no new cards, return None to end game
        if (cards.isEmpty) {
            if (!newCards.isEmpty) {
                // Reset their cards with the won cards
                cards = newCards
                newCards = Queue[Card]()

                val dequeuedCard = cards.dequeue
                returnCard = Some(dequeuedCard._1)
                cards = dequeuedCard._2
            }
        }
        else {
            // Return the next card from the current set
            val dequeuedCard = cards.dequeue
            returnCard = Some(dequeuedCard._1)
            cards = dequeuedCard._2
        }

        returnCard
    }

    def addCards(wonCards: Seq[Card]) = {
        // Prepend all won cards
        newCards = newCards ++ wonCards
    }
}

object Deck extends App {
  println("Playing war!")

  val shuffledDeck = scala.util.Random.shuffle(
    Seq(
        Card(1, "2 of Clubs"),
        Card(2, "3 of Clubs"),
        Card(3, "4 of Clubs"),
        Card(4, "5 of Clubs"),
        Card(5, "6 of Clubs"),
        Card(6, "7 of Clubs"),
        Card(7, "8 of Clubs"),
        Card(8, "9 of Clubs"),
        Card(9, "10 of Clubs"),
        Card(10, "Jack of Clubs"),
        Card(11, "Queen of Clubs"),
        Card(12, "King of Clubs"),
        Card(13, "Ace of Clubs"),

        Card(14, "2 of Diamonds"),
        Card(15, "3 of Diamonds"),
        Card(16, "4 of Diamonds"),
        Card(17, "5 of Diamonds"),
        Card(18, "6 of Diamonds"),
        Card(19, "7 of Diamonds"),
        Card(20, "8 of Diamonds"),
        Card(21, "9 of Diamonds"),
        Card(22, "10 of Diamonds"),
        Card(23, "Jack of Diamonds"),
        Card(24, "Queen of Diamonds"),
        Card(25, "King of Diamonds"),
        Card(26, "Ace of Diamonds"),

        Card(27, "2 of Hearts"),
        Card(28, "3 of Hearts"),
        Card(29, "4 of Hearts"),
        Card(30, "5 of Hearts"),
        Card(31, "6 of Hearts"),
        Card(32, "7 of Hearts"),
        Card(33, "8 of Hearts"),
        Card(34, "9 of Hearts"),
        Card(35, "10 of Hearts"),
        Card(36, "Jack of Hearts"),
        Card(37, "Queen of Hearts"),
        Card(38, "King of Hearts"),
        Card(39, "Ace of Hearts"),

        Card(40, "2 of Spades"),
        Card(41, "3 of Spades"),
        Card(42, "4 of Spades"),
        Card(43, "5 of Spades"),
        Card(44, "6 of Spades"),
        Card(45, "7 of Spades"),
        Card(46, "8 of Spades"),
        Card(47, "9 of Spades"),
        Card(48, "10 of Spades"),
        Card(49, "Jack of Spades"),
        Card(50, "Queen of Spades"),
        Card(51, "King of Spades"),
        Card(52, "Ace of Spades")
    )
  )
  println(s"Shuffled deck: $shuffledDeck")


  val wargame = new WarGame().play(shuffledDeck)
}

class WarGame {
    def play(shuffledDeck: Seq[Card]) = {
        // Deal the shuffled cards, one at a time to each
        val (player1Cards, player2Cards) = Deck.shuffledDeck.partition(_.id % 2 == 0)

        // Set up the players with their cards, and play
        val player1 = Player("Layla", Queue.from(player1Cards))
        val player2 = Player ("Sophie", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        while (p1Card != None && p2Card != None) {
            val winner = playHand(player1, player2, p1Card, p2Card)
            winner.addCards(List(p1Card.get, p2Card.get))
            p1Card = player1.getCard
            p2Card = player2.getCard
        }

        println(s"\n\n================================================\nFINISHED!")
    }

    def playHand(player1: Player, player2: Player, p1Card: Option[Card], p2Card: Option[Card]): Player = {
        var winner: Player = null

        // We don't care about suit, we only care if the card is stronger than the other, or equivalent.  So
        // we use modulus to determine which card and player wins, or if there is a war as a result of having
        // equivalent cards.
        val result = (p1Card.get.id % 13) - (p2Card.get.id % 13)
        if (result > 0) {
            println(s"${player1.name} WINS with $result! P1: ${p1Card.get}, P2: ${p2Card.get}")
            winner = player1
        }
        else if (result < 0) {
            println(s"${player2.name} WINS with $result! P1: ${p1Card.get}, P2: ${p2Card.get}")
            winner = player2
        }
        else {
            // WAR!  Get 2 cards from each to hold as winnings, and then play the next two
            println("*********** War! ***********")
            val holdCards: Seq[Card] = List()
            player1.getCard.map(_ +: holdCards)
            player1.getCard.map(_ +: holdCards)
            player2.getCard.map(_ +: holdCards)
            player2.getCard.map(_ +: holdCards)

            val newP1Card = player1.getCard
            val newP2Card = player2.getCard
            if (newP1Card != None && newP2Card != None)
                winner = playHand(player1, player2, newP1Card, newP2Card)
            else if (newP1Card != None && newP2Card == None)
                winner = player1
            else if (newP1Card == None && newP2Card != None)
                winner = player2
//            else
                // who wins if they both ran out?

            winner.addCards(holdCards)
        }

        println(s"Player one: $player1, Player 2: $player2")
        winner
    }
}