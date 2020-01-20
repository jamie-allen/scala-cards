package com.jamieallen.scalacards.war

import scala.collection.immutable.Queue
import org.scalatest.WordSpecLike
import org.scalatest.Matchers

class WarSpec extends WordSpecLike with Matchers {
  "Playing a hand" should {
    "return a Player2 as winner" in {
        // Deal the shuffled cards, one at a time to each
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))
        val player2Cards = Seq(Card(44, "6 of Spades"), Card(45, "7 of Spades"), Card(46, "8 of Spades"))


        // Set up the players with their cards, and play
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return a Player1 as winner" in {
        // Deal the shuffled cards, one at a time to each
        val player1Cards = Seq(Card(44, "6 of Spades"), Card(45, "7 of Spades"), Card(46, "8 of Spades"))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))


        // Set up the players with their cards, and play
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }

    "return player2 as a winner when a war takes place with player2 having the better 4th card" in {
        // Deal the shuffled cards, one at a time to each
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"), Card(4, "5 of Clubs"))
        val player2Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))


        // Set up the players with their cards, and play
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return player1 as a winner when a war takes place with player1 having the better 4th card" in {
        // Deal the shuffled cards, one at a time to each
        val player1Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"), Card(4, "5 of Clubs"))


        // Set up the players with their cards, and play
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        // Get their first cards and start
        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }
/*
    "should replenish their card queue with winnings when they run out" in {
      val player = Player("foo", Queue(Card(1, "blah"), Card(2, "blue")))

      // Pretend the player won this round
      player.getCard should equal(Some(Card(1, "blah")))
      player.addCards(List(Card(1, "blah"), Card(3, "bling")))

      // Deplete their remaining current set of cards
      player.getCard should equal(Some(Card(2, "blue")))

      // Next getCard should force them to use the won cards
      player.getCard should equal(Some(Card(1, "blah")))
      player.getCard should equal(Some(Card(3, "bling")))

      // Now they should be out
      player.getCard should equal(None)
    }
*/
  }
}
