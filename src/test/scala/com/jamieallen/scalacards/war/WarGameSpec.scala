package com.jamieallen.scalacards.war

import scala.collection.immutable.Queue
import org.scalatest.WordSpecLike
import org.scalatest.Matchers

import com.jamieallen.scalacards.Card

class WarSpec extends WordSpecLike with Matchers {
  "Playing a hand" should {
    "return a Player2 as winner" in {
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(44, "6 of Spades"), Card(45, "7 of Spades"), Card(46, "8 of Spades"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return a Player1 as winner" in {
        val player1Cards = Seq(Card(44, "6 of Spades"), Card(45, "7 of Spades"), Card(46, "8 of Spades"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }

    "return player2 as a winner when a war takes place with player1 having only 1 cards" in {
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return player2 as a winner when a war takes place with player1 having only 2 cards" in {
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return player2 as a winner when a war takes place with player1 having only 3 cards" in {
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return player2 as a winner when a war takes place with player2 having the better 4th card" in {
        val player1Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"), Card(4, "5 of Clubs"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player2)
    }

    "return player1 as a winner when a war takes place with player2 having only 1 card" in {
        val player1Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(14, "2 of Diamonds"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }

    "return player1 as a winner when a war takes place with player1 having only 2 cards" in {
        val player1Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }

    "return player1 as a winner when a war takes place with player1 having only 3 cards" in {
        val player1Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }

    "return player1 as a winner when a war takes place with player1 having the better 4th card" in {
        val player1Cards = Seq(Card(27, "2 of Hearts"), Card(45, "7 of Spades"), Card(46, "8 of Spades"), Card(50, "Queen of Spades"))
        val player1 = Player("Test1", Queue.from(player1Cards))
        val player2Cards = Seq(Card(14, "2 of Diamonds"), Card(15, "3 of Diamonds"), Card(16, "4 of Diamonds"), Card(4, "5 of Clubs"))
        val player2 = Player ("Test2", Queue.from(player2Cards))

        var p1Card = player1.getCard
        var p2Card = player2.getCard
        new WarGame().playHand(player1, player2, p1Card, p2Card) should equal(player1)
    }
  }
}
