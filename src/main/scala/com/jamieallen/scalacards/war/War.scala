/**************************************************************************************
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
 ***************************************************************************************/

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

object WarGame extends App {
    val winner = new WarGame().play(Deck.shuffledDeck)
    println(s"\n\n================================================\nFINISHED! The winner is ${winner.name}")
}

class WarGame {
    def play(shuffledDeck: Seq[Card]): Player = {
        // Deal the shuffled cards, one at a time to each
        val (player1Cards, player2Cards) = Deck.shuffledDeck.partition(_.id % 2 == 0)

        // Set up the players with their cards, and play
        val player1 = Player("Layla", Queue.from(player1Cards))
        val player2 = Player ("Sophie", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        var winner: Player = null
        while (p1Card != None && p2Card != None) {
            winner = playHand(player1, player2, p1Card, p2Card)
            winner.addCards(List(p1Card.get, p2Card.get))
            p1Card = player1.getCard
            p2Card = player2.getCard
        }

        winner
    }

    def playHand(player1: Player, player2: Player, p1Card: Option[Card], p2Card: Option[Card]): Player = {
        var winner: Player = null

        // We don't care about suit, we only care if the card is stronger than the other, or equivalent.  So
        // we use modulus to determine which card and player wins, or if there is a war as a result of having
        // equivalent cards.
        val result = (p1Card.get.id % 13) - (p2Card.get.id % 13)
        if (result > 0) {
            println(s"${player1.name} WINS with $result! P1: $p1Card, P2: $p2Card")
            winner = player1
        }
        else if (result < 0) {
            println(s"${player2.name} WINS with $result! P1: $p1Card, P2: $p2Card")
            winner = player2
        }
        else {
            // WAR!  Get 2 cards from each to hold as winnings, and then play the next two
            println("*********** War! Feel my wrath! ***********")
            var holdCards: List[Card] = List()

            // Get cards to hold to the side for this war from each player; the 
            // braces here limit the scope of the first hold cards
            var newP1Card: Option[Card] = None
            var newP2Card: Option[Card] = None
            
            {
                val p1HoldCard1 = player1.getCard
                val p2HoldCard1 = player2.getCard

                // Make sure neither are out of cards after the first
                p1HoldCard1 match {
                    case Some(card) if p2HoldCard1 == None => newP2Card = p2Card
                    case Some(card) => {
                        holdCards = p1HoldCard1.get :: holdCards
                        holdCards = p2HoldCard1.get :: holdCards
                    }
                    case None if p2HoldCard1 == None => {
                        // Who wins if they both ran out?  The official rules say this is a draw
                        println("Both players are out of cards, this a is a DRAW!")
                        System.exit(1)
                    }
                    case _ => newP1Card = p1Card
                }

                // Make sure neither are out of cards after the second
                if (winner == null) {
                    val p1HoldCard2 = player1.getCard
                    val p2HoldCard2 = player2.getCard
                    p1HoldCard2 match {
                        case Some(card) if p2HoldCard2 == None => {
                            newP2Card = if (newP2Card == None) p2HoldCard1 else newP2Card
                        }
                        case Some(card) => {
                            holdCards = p1HoldCard2.get :: holdCards
                            holdCards = p2HoldCard2.get :: holdCards
                        }
                        case None if p2HoldCard2 == None => {
                            // Who wins if they both ran out?  The official rules say this is a draw
                            println("Both players are out of cards, this a is a DRAW!")
                            System.exit(1)
                        }
                        case _ => newP2Card = p2Card
                    }
                }
            }

            // If both players have enough cards, recursively play war until someone wins
            if (winner == null) {
                newP1Card = if (newP1Card == None) player1.getCard else newP1Card
                newP2Card = if (newP2Card == None) player2.getCard else newP2Card
                if (newP1Card != None && newP2Card != None)
                    winner = playHand(player1, player2, newP1Card, newP2Card)
                else if (newP1Card != None && newP2Card == None)
                    winner = player1
                else if (newP1Card == None && newP2Card != None)
                    winner = player2
                else {
                    // Who wins if they both ran out?  The official rules say this is a draw
                    println("Both players are out of cards, this a is a DRAW!")
                    System.exit(1)
                }

                winner.addCards(holdCards)
            }
        }

        if (winner == null) {
            // Something went very wrong with my logic, we should always have a winner
            throw new Exception("No winner!")
        }

        winner
    }
}