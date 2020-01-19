package com.jamieallen.scalacards.war

import scala.collection.immutable.Queue
import org.scalatest.WordSpecLike
import org.scalatest.Matchers

class WarSpec extends WordSpecLike with Matchers {
  "Playing a hand" should {
    "return a winner and update won cards for player" in {
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
