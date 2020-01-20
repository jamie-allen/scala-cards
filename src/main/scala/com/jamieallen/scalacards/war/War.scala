/********************************************************
 * Copyright Jamie Allen, 2020
 *
 * Why yes, this is a flower box.  I'm old school.
 *
 * War is a card game, usually played by two players.  Shuffled cards are dealt out 
 * evenly to each player, and the players place one card down at a time against each 
 * other to win.  Whomever has the highest card (where 2 is the lowest and Ace is the
 * highest) wins the hand.
 *
 * It is possible for two cards to be the same value but from different suits (ie, 
 * player 1 plays the 7 of spades, and player 2 plays the 7 of diamonds).  When this 
 * happens, a "war" occurs.  Each player deals 2 cards (without showing what they are), 
 * and then flips up the third.  Whomever has the highest of those two cards wins all 
 * of the cards set aside in that battle.  If a match happens again, the players do it 
 * again until someone wins the hand.
 *
 * The object is to win all of the cards from the other player.  If a player runs out 
 * of cards in their current deck, they pick up the stack they've won and begin to play 
 * from that.  This happens until one of the players runs out of all of their cards.
 *
 * I've coded this is in Scala as an interesting programming exercise, because it 
 * involves recursion and other interesting rules.  And because I've missed programming.
 *
 ********************************************************/

package com.jamieallen.scalacards.war

import com.jamieallen.scalacards._
import scala.collection.immutable.Queue

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

object WarGame {
    new WarGame().play(Deck.shuffledDeck)
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