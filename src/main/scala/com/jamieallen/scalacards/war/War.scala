package com.jamieallen.scalacards.war

case class Card(card: Int, name: String)

object War extends App {
  println("Playing war!")

  val deck = Seq(
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

  println(s"Deck: $deck")

  val shuffledDeck = scala.util.Random.shuffle(deck)
  println(s"Shuffled deck: $shuffledDeck")
}

class War {
}