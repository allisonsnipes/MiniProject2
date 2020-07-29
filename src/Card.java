/**
 * Allison Snipes
 * Course 605.201.81 Summer 2020
 * Mini Project 2: Blackjack Game Simulator
 * 
 *  Project Specs:
 *  A simple blackjack card game consists of a player and a dealer. A player is provided with a sum of money with which 
 *  to play. A player can place a bet between $0 and the amount of money the player has. A player is dealt cards, called 
 *  a hand. Each card in the hand has a point value. The objective of the game is to get as close to 21 points as possible
 *  without exceeding 21 points. A player that goes over is out of the game. The dealer deals cards to itself and a player.
 *  The dealer must play by slightly different rules than a player, and the dealer does not place bets. A game proceeds as
 *  follows: A player is dealt two cards face up. If the point total is exactly 21 the player wins immediately. If the total
 *  is not 21, the dealer is dealt two cards, one face up and one face down. A player then determines whether to ask the 
 *  dealer for another card (called a “hit”) or to “stay” with his/her current hand. A player may ask for several “hits.”
 *  When a player decides to “stay” the dealer begins to play. If the dealer has 21 it immediately wins the game. 
 *  Otherwise, the dealer must take “hits” until the total points in its hand is 17 or over, at which point the dealer must 
 *  “stay.” If the dealer goes over 21 while taking “hits” the game is over and the player wins. If the dealer’s points total
 *  exactly 21, the dealer wins immediately. When the dealer and player have finished playing their hands, the one with the 
 *  highest point total is the winner. Play is repeated until the player decides to quit or runs out of money to bet.
 *  
 *  @author Allison Snipes
 *  @version 1.0
 */

/**
 * This class is responsible for creating the cards in the deck.
 *
 */

public class Card {
	//so other classes or methods cant change or mess with it
	private int number, suit, rank;
	
	private static String[] suits = {"Spades", "Diamonds", "Clubs", "Hearts"};
	private static String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Joker", "Queen", "King"};
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @return the suit of the card
	 *
	 */
	public int getSuit() {
		return suit;
	}
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @param suit sets the suit's characteristic
	 *
	 */
	
	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @return the rank of the card
	 *
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @param rank sets the rank of the card
	 *
	 */
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @return the number of the specific card
	 *
	 */
	public int getSpecificCard() {
		if (rank == 1) {
			number = 11;
		} else if (rank > 10) {
			number = 10;
		} else {
			number = rank;
		}
		
		return number;
	} 
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @param number sets the number of the specific card
	 *
	 */
	public void setSpecificCard(int set) {
		this.number = set;
	}
	
	Card (int suits, int specificCard) {
		this.suit = suit;
		this.rank = specificCard;
	}
	
	/**
	 * This class is responsible for creating the cards in the deck.
	 * @return the suit and sumber of the card
	 *
	 */
	
	public String toString() {
		return ranks[rank] + " of suit " + suits[suit];
	}
}
