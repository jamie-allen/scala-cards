package com.jamieallen.scalacards.war

case class Card(id: Int, name: String)
case class Player(name: String, var cards: Seq[Card])

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

  val war = new War()
}

class War {
    val (player1Cards, player2Cards) = War.shuffledDeck.partition(_.id % 2 == 0)

    val player1 = Player("Layla", player1Cards)
    val player2 = Player ("Daddy", player2Cards)
    println(s"\n\nPLAYER 1: $player1")
    println(s"\n\nPLAYER 2: $player2")

    var newPlayer1Cards: Seq[Card] = List()
    var newPlayer2Cards: Seq[Card] = List()

    var index1, index2, player1Wins, player2Wins = 0
    while (index1 < player1.cards.length) {
        var p1Card: Card = null
        var p2Card: Card = null
        p1Card = player1.cards(index1)
        
        if (index2 >= player2.cards.length) {
            if (player2Cards.length == 0)
                exitGame()
            else {
                index2 = 0
                player2.cards = player2Cards
            }
        }

        p2Card = player2.cards(index2)
        index2 += 1

        if ((p1Card.id % 13) > (p2Card.id % 13)) {
            println(s"${player1.name} WINS $index1! P1: $p1Card, P2: $p2Card")
            newPlayer1Cards = (newPlayer1Cards :+ p1Card) :+ p2Card
            player1Wins += 1
        }
        else {
            println(s"${player2.name} WINS $index2! P1: $p1Card, P2: $p2Card")
            newPlayer2Cards = (newPlayer2Cards :+ p1Card) :+ p2Card
            player2Wins += 1
        }

        index1 += 1
        if (index1 >= player1.cards.length) {
            if (player1Cards.length == 0)
                exitGame()
            else {
                index1 = 0
                player1.cards = player1Cards
            }
        }
    }

    player1.cards = newPlayer1Cards
    player2.cards = newPlayer2Cards

    println(s"FINISHED!\n\nPlayer1 won $player1Wins times, cards: ${player1.cards}\n\nPlayer2 won $player2Wins times, cards: ${player2.cards}")

    def exitGame() = System.exit(0)
}