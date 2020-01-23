/**************************************************************************************
 * Copyright Jamie Allen, 2020
 *
 * Fan Tan is a simple card game, but has unique tactical decision-making rules.  The 
 * object of the game is to play all of your cards before anyone else.  The cards are 
 * organized by suit, starting with the 7.  From there, players can play cards upwards
 * to the King of that suit, or downwards to the Ace of that suit, in sequence.  A
 * player will want to hold onto cards that impact other players' abilities to go out
 * before them - for example, if they have the 6 of hearts, and no other low hearts to
 * play below it (such as a three), they will want to hold onto that 6 as long as they
 * can, so that other players who depend on it can't go out.  The more lower/higher cards
 * they have of a suit, the more important it is for them to play those cards first, if
 * they can.  And they cannot pass, if they have a valid play to make.
 * 
 ***************************************************************************************/

package com.jamieallen.scalacards.fantan

import com.jamieallen.scalacards._
import scala.collection.immutable.Queue
import scala.io.StdIn.readLine

case class Player(name: String, var cards: Set[Card]) {
    def playCard: Option[Card] = {
        None
    }
}

object FanTanGame extends App {
    val player1Name = readLine("Please enter the name of Player1: ")
    val player2Name = readLine("Please enter the name of Player2: ")
    val player3Name = readLine("Please enter the name of Player3: ")
    val player4Name = readLine("Please enter the name of Player4: ")
    val winner = new FanTanGame(player1Name, player2Name, player3Name, player4Name).play(Deck.shuffledDeck)
    println(s"\n================================================\nFINISHED! The winner is ${winner.name}")
}

class FanTanGame(player1Name: String, player2Name: String, player3Name: String, player4Name: String) {
    def play(shuffledDeck: Seq[Card]): Player = {
        // Deal the shuffled cards, one at a time to each
        val splitCards = Deck.shuffledDeck.groupBy(_.id % 4)

        // Set up the players with their cards, and play
        val player1 = Some(Player(player1Name, splitCards(0).toSet))
        val player2 = Some(Player(player2Name, splitCards(1).toSet))
        val player3 = Some(Player(player3Name, splitCards(2).toSet))
        val player4 = Some(Player(player4Name, splitCards(3).toSet))

        var winner: Player = null
        var play = true

        // Find the player with the 7 of clubs, and start with them

        while (play) {
            // Check to see if there is more than 1 player remaining (all others are None)
            play = false


        }

        winner
    }
}