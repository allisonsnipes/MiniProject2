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

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for creating the deck that will be dealt. We begin by creating the deck via an array, 
 * and for loops for both the suit and number of the card.
 *
 */

public class Deck {
	private ArrayList<Card> deck;
	
	Deck(){
		deck = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 13; j++) {
				deck.add(new Card(i,j));
			}
		}
	}
	
	/**
	 * This coding block is responsible for shuffling the cards randomly to deal. We need to be able to gather
	 * random pairs of the cards. We will need to use the card class in order for this section to work.
	 */	
	
	public void mixCards() {
		Random shuffleCards = new Random();
		Card holder;
		for (int i = 0; i <= 52; i++) {
			int shuffleOne = shuffleCards.nextInt(deck.size() - 1);
			int shuffleTwo = shuffleCards.nextInt(deck.size() - 1);
			holder = deck.get(shuffleTwo);
			deck.set(shuffleTwo, deck.get(shuffleOne));
			deck.set(shuffleOne, holder);
		}
	}
	
	/**
	 * Draws a random card from the top and shifts everything in the array to the left.
	 */	
	public Card draw() {
		return deck.remove(0);
	}
	
	
}
