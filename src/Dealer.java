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

/**
 * This is the class that is responsible for the Dealer in the game. We start by building the 
 * dealer's hand.
 *
 */
public class Dealer {
	ArrayList<Card> hand;
	private int dealerHand = 0;
	private Card[] dHand;
	private int dAces;
	
	Dealer(Deck deck) {
		hand = new ArrayList<>();
		dHand = new Card[] {};
		int dAces = 0;
		for (int i = 0; i < 2; i++) {
			hand.add(deck.draw());
		}
		dHand = hand.toArray(dHand);
		for (int i = 0; i < dHand.length; i++) {
			dealerHand += dHand[i].getSpecificCard();
			if(dHand[i].getSpecificCard() == 11) {
				dAces++;
			}
			while(dAces > 0 && dealerHand > 21) {
				dealerHand -= 10;
				dAces--;
			}
		}
	}
	
	/**
	 * This method will display one of the Dealer's cards.
	 *
	 *
	 */
	
	public void showOne() {
		Card[] oneCard = new Card[] {};
		oneCard = hand.toArray(oneCard);
		System.out.println(oneCard[0]);
		
	}
	
	/**
	 * This method allows for the Dealer to draw again for their second card.
	 *
	 * @param deck allows the Dealer to draw from the deck
	 *
	 */
	
	public void hit(Deck deck) {
		hand.add(deck.draw());
		dHand = hand.toArray(dHand);
		dealerHand = 0;
		
		for(int i = 0; i < dHand.length; i++) {
			dealerHand += dHand[i].getSpecificCard();
			if(dHand[i].getSpecificCard() == 11) {
				dAces++;
			}
			
			while (dAces > 0 && dealerHand > 21) {
				dealerHand -= 10;
				dAces--;
			}
		}
	}
	
	
	/**
	 * This method will determine the dealer's strategy of hitting or staying.
	 * @ return true if the dealer remains under 17 they may draw again
	 * @return false if the dealer is over 17 they should not draw again
	 */
	
	public boolean hitAgain() {
		if(dealerHand < 17) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method will determine if the dealer is the winner of the round and has Black Jack.
	 * @return true if the dealer has a blackjack
	 * @return false if the dealer does not have blackjack
	 *
	 */
	
	public boolean dealerWins() {
		if(hand.size() == 2 && dealerHand == 21) {
			System.out.println("The Dealer has Black Jack!");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method will determine if the dealer lost the round.
	 * @param dealerHand2 checks the dealer hand if it is over 21
	 * @return true if the dealer went over 21
	 * @return false if the dealer did not go over 21
	 *
	 */
	
	public boolean dealerLooses(int dealerHand) {
		if(dealerHand > 21) {
			System.out.println("The Dealer went over 21.");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method will grab the dealer's hand.
	 * @return the value of the dealer's hand
	 *
	 */
	
	public int getHand() {
		return dealerHand;
	}
	
	
	/**
	 * This method will display the Dealer's hand.
	 *
	 */
	
	public void showDealerHand() {
		System.out.println(hand);
	}
	
	/**
	 * This method is responsible for interaction with the player and displays the dealer hand.
	 *
	 * @param deck allows the Dealer to draw from the deck
	 * @return the dealer hand
	 *
	 */
	
	public int interactionDealer(Deck deck) {
		while (hitAgain()) {
			System.out.println("The Dealer chooses to hit.");
			hit(deck);
			if(dealerLooses(dealerHand)) {
				break;
			}
		}
		
		if(dealerHand <=21) {
			System.out.println("The Dealer chooses to stand.");
		}
		return dealerHand;
	}
}
