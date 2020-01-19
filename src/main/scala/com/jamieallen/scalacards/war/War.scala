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

object War extends App {
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


  val war = new War().play(shuffledDeck)
}

class War {
    def play(shuffledDeck: Seq[Card]) = {
        // Deal the shuffled cards, one at a time to each
        val (player1Cards, player2Cards) = War.shuffledDeck.partition(_.id % 2 == 0)

        // Set up the players with their cards, and play
        val player1 = Player("Layla", Queue.from(player1Cards))
        val player2 = Player ("Sophie", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        while (p1Card != None && p2Card != None) {
            playHand(player1, player2, p1Card, p2Card)
            p1Card = player1.getCard
            p2Card = player2.getCard
        }

        println(s"\n\n================================================\nFINISHED!")
    }

    def playHand(player1: Player, player2: Player, p1Card: Option[Card], p2Card: Option[Card]): Unit = {
        if ((p1Card.get.id % 13) > (p2Card.get.id % 13)) {
            println(s"${player1.name} WINS! P1: ${p1Card.get}, P2: ${p2Card.get}")
            player1.addCards(List(p1Card.get, p2Card.get))
        }
        else if ((p1Card.get.id % 13) == (p2Card.get.id % 13)) {
            // WAR!
        }
        else {
            println(s"${player2.name} WINS! P1: ${p1Card.get}, P2: ${p2Card.get}")
            player2.addCards(List(p1Card.get, p2Card.get))
        }

        println(s"Player one: $player1, Player 2: $player2")
    }
}