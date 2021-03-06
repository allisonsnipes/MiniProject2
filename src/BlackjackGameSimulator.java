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
import java.util.List;
import java.util.Scanner;

/**
 * This is the main method of the main class
 *
 */
public class BlackjackGameSimulator {

	public static void main(String[] args) {
		headerMsg();
		int bet, cash, playerHand;

		System.out.println("How much money would you like to put in your reserves?\n");
		Scanner input = new Scanner(System.in);
		cash = input.nextInt();
		System.out.println("\nYou have $" + cash + " in your reserves\n");

		System.out.println("\nHow much money would you like to bet?\n");
		bet = input.nextInt();
		System.out.println("\nYou placed a $" + bet + " bet. You have $" + (cash - bet) + " in your reserves.\n");

		if (bet > cash) {
			System.out.print("\nYou cannot bet more money than what you have in reserves. Please try again\n");
			bet = input.nextInt();
		}

		while (cash > 0){
			Deck deck = new Deck();
			deck.mixCards();
			Dealer dealer = new Dealer(deck);
			List<Card> hand = new ArrayList<>();
			hand.add(deck.draw());
			hand.add(deck.draw());
			System.out.print("\nLet's see your hand and what you're working with: ");
			System.out.println(hand);
			playerHand = PairValue(hand);

			System.out.print("\nThis is what the Dealer is working with: ");
			dealer.showOne();

			System.out.println("\nWould you like to stay (s) or hit (h)?");
			Scanner newInput = new Scanner(System.in);
			String choice = newInput.nextLine();

			if(choice.equals("h")) {
				PlayerHit(deck, hand);
				System.out.print("\nThis is your new hand: ");
				System.out.println(hand);
				playerHand = PairValue(hand);

				if(NextRound(playerHand)) {
					Lost();
					System.out.println("\nYou lost your bet of $" + bet + ".");
					cash = cash - bet;
					System.out.println("\nYour cash reserve is now $" + cash + ".");

				} else if(playerWins(playerHand) && dealer.dealerWins()) {
					Tie();
					System.out.println("\nPlayer you will get $" + bet + "back.");
				} else if (playerWins(playerHand)) {
					Wins();
					System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
				} 

			} else if(choice.equals("s")) {
				int dealerHand = dealer.interactionDealer(deck);
				System.out.print("\nThis is what the Dealer is working with: ");
				dealer.showDealerHand();

				if(dealerHand > 21 ) {
					Wins();
					System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");

				} else {
					int dealerNumber = 21 - dealerHand;
					int playerNumber = 21 - playerHand;
					if (playerNumber > dealerNumber) {
						Lost();
						System.out.println("\nYou lost your bet of $" + bet + ".");
						cash = cash - bet;
						System.out.println("\nYour cash reserve is now $" + cash + ".");

					} 
					if (playerNumber < dealerNumber) {
						Wins();
						System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
						System.out.println("\nJob well done!");

					}
					if (playerNumber == dealerNumber){
						Tie();
						System.out.println("\nPlayer you will get $" + bet + "back.");

					}
				}
			}
		}


		while (cash <= 0) {
			System.out.println("\nThank you for playing, unfornuately you do not have any more money to play with.");
			System.out.println("\nWould you like to gamble again? Please answer (Y)/(N).");
			String response = input.nextLine();

			if(response.equals("y")){
				System.out.println("How much money would you like to put in your reserves?\n");
				Scanner input2 = new Scanner(System.in);
				int cash2 = input2.nextInt();

				while (cash2 > 0) {
					System.out.println("\nWould you like to stay (s) or hit (h)?");
					Scanner newInput = new Scanner(System.in);
					String choice = newInput.nextLine();

					if (choice.equals("h")) {
						Deck deck5 = new Deck();
						deck5.mixCards();
						Dealer dealer5 = new Dealer(deck5);
						List<Card> hand5 = new ArrayList<>();
						hand5.add(deck5.draw());
						hand5.add(deck5.draw());
						System.out.print("\nLet's see your hand and what you're working with: ");
						System.out.println(hand5);
						int playerHand5 = PairValue(hand5);

						System.out.print("\nThis is what the Dealer is working with: ");
						dealer5.showOne();

						if(playerWins(playerHand5) && dealer5.dealerWins()) {
							Tie();
							System.out.println("\nPlayer you will get $" + bet + "back.");
							break;
						} else if (playerWins(playerHand5)) {
							Wins();
							System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
							break;
						} else if (dealer5.dealerWins()) {
							Lost();
							System.out.println("\nYou lost your bet of $" + bet + ".");
							cash = cash - bet;
							System.out.println("\nYour cash reserve is now $" + cash + ".");	
							break;
						}
					} else if (choice.equals("s")) {
						Deck deck = new Deck();
						deck.mixCards();
						Dealer dealer = new Dealer(deck);
						int dealerHand = dealer.interactionDealer(deck);
						System.out.print("\nThis is what the Dealer is working with: ");
						dealer.showDealerHand();
						List<Card> hand = new ArrayList<>();
						hand.add(deck.draw());
						hand.add(deck.draw());
						int playerHand5 = PairValue(hand);

						if(dealerHand > 21 ) {
							Wins();
							System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");

						} else {
							int dealerNumber = 21 - dealerHand;
							int playerNumber = 21 - playerHand5;
							if (playerNumber > dealerNumber) {
								Lost();
								System.out.println("\nYou lost your bet of $" + bet + ".");
								cash = cash - bet;
								System.out.println("\nYour cash reserve is now $" + cash + ".");

							} 
							if (playerNumber < dealerNumber) {
								Wins();
								System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
								System.out.println("\nJob well done!");

							}
							if (playerNumber == dealerNumber){
								Tie();
								System.out.println("\nPlayer you will get $" + bet + "back.");

							}
						}
					}
				}
			}

			while (cash == 0) {
				System.out.println("\nThank you for playing, unfornuately you do not have any more money to play with.");
				System.out.println("\nWould you like to gamble again? Please answer (Y)/(N).");
				Scanner inputTwo = new Scanner(System.in);
				String responseTwo = inputTwo.nextLine();

				if(responseTwo.equals("y")){
					System.out.println("How much money would you like to put in your reserves?\n");
					input = new Scanner(System.in);
					cash = input.nextInt();
					System.out.println("How much money would you like to bet?\n");
					bet = input.nextInt();

					Deck deck6 = new Deck();
					deck6.mixCards();
					Dealer dealer6 = new Dealer(deck6);
					List<Card> hand6 = new ArrayList<>();
					hand6.add(deck6.draw());
					hand6.add(deck6.draw());
					
					System.out.println("\nWould you like to stay (s) or hit (h)?");
					String choice = input.nextLine();
					
					while (cash > 0) {
						if (choice.equals("h")) {
							System.out.print("\nLet's see your hand and what you're working with: ");
							System.out.println(hand6);
							int playerHand6 = PairValue(hand6);

							System.out.print("\nThis is what the Dealer is working with: ");
							dealer6.showOne();

							if(playerWins(playerHand6) && dealer6.dealerWins()) {
								Tie();
								System.out.println("\nPlayer you will get $" + bet + "back.");
								break;
							} else if (playerWins(playerHand6)) {
								Wins();
								System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
								break;
							} else if (dealer6.dealerWins()) {
								Lost();
								System.out.println("\nYou lost your bet of $" + bet + ".");
								cash = cash - bet;
								System.out.println("\nYour cash reserve is now $" + cash + ".");	
								break;
							}
						} else if (choice.equals("s")) {
							Deck deck7 = new Deck();
							deck7.mixCards();
							Dealer dealer7 = new Dealer(deck7);
							int dealerHand7 = dealer7.interactionDealer(deck7);
							System.out.print("\nThis is what the Dealer is working with: ");
							dealer7.showDealerHand();
							hand6.add(deck7.draw());
							hand6.add(deck7.draw());
							int playerHand6 = PairValue(hand6);

							if(dealerHand7 > 21 ) {
								Wins();
								System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");

							} else {
								int dealerNumber = 21 - dealerHand7;
								int playerNumber = 21 - playerHand6;
								if (playerNumber > dealerNumber) {
									Lost();
									System.out.println("\nYou lost your bet of $" + bet + ".");
									cash = cash - bet;
									System.out.println("\nYour cash reserve is now $" + cash + ".");

								} 
								if (playerNumber < dealerNumber) {
									Wins();
									System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
									System.out.println("\nJob well done!");

								}
								if (playerNumber == dealerNumber){
									Tie();
									System.out.println("\nPlayer you will get $" + bet + "back.");

								}
							}

						}
					}

					System.out.println("\nWould you like to gamble again? Please answer (Y)/(N).");
					input = new Scanner(System.in);
					String responseThree = input.nextLine();

					if(responseThree.equals("y")){
						System.out.println("How much money would you like to put in your reserves?\n");
						input = new Scanner(System.in);
						cash = input.nextInt();

						Deck deck3 = new Deck();
						deck3.mixCards();
						Dealer dealer3 = new Dealer(deck3);
						List<Card> hand3 = new ArrayList<>();
						hand3.add(deck3.draw());
						hand3.add(deck3.draw());
						
						while (cash > 0) {
							System.out.print("\nLet's see your hand and what you're working with: ");
							System.out.println(hand3);
							int playerHand3 = PairValue(hand3);

							System.out.print("\nThis is what the Dealer is working with: ");
							dealer3.showOne();

							if(playerWins(playerHand3) && dealer3.dealerWins()) {
								Tie();
								System.out.println("\nPlayer you will get $" + bet + "back.");
								break;
							} else if (playerWins(playerHand3)) {
								Wins();
								System.out.println("\nYour new cash reserves is $" + (cash + bet) + ".");
								break;
							} else if (dealer3.dealerWins()) {
								Lost();
								System.out.println("\nYou lost your bet of $" + bet + ".");
								cash = cash - bet;
								System.out.println("\nYour cash reserve is now $" + cash + ".");	
								break;
							}
						}
					} 
					
					if (responseThree.equals("n")) {
						break;
					}
				}
			}
		}
	}

	/**
	 * This is adds up the players cards to see if they have Black Jack.
	 * @param hand the array of cards in the player's hand of cards
	 * @return playerHand the player's hand of cards
	 *
	 */

	public static int PairValue(List<Card> hand) {
		Card[] dHand = new Card[] {};
		dHand = hand.toArray(dHand);
		int playerHand = 0;
		int pAces = 0;
		for (int i =0; i < dHand.length; i++) {
			playerHand += dHand[i].getSpecificCard();
			if (dHand[i].getSpecificCard() == 11) {
				pAces++;
			}

			while(pAces > 0 && playerHand > 21) {
				playerHand -= 10;
				pAces--;
			}
		}

		return playerHand;
	}

	/**
	 * This is the necessary condition for the user to have a Black Jack.
	 * @param playerHand the player's hand of cards
	 * @return true when the player wins if their hand equals to 21
	 * @return false when the player does not win if their hand is not equal to 21
	 *
	 */

	public static boolean playerWins(int playerHand) {
		if(playerHand ==21) {
			return true;
		}
		return false;
	}

	/**
	 * This will be executed if there is a tie between the dealer and player.
	 *
	 */
	public static void Tie() {
		System.out.println("There is a tie, no money was lost--but no money gained.");

	}

	/**
	 * This will be executed if the player wins.
	 *
	 */
	public static void Wins() {
		System.out.println("Player you won!");

	}

	/**
	 * This will be executed if the player looses.
	 *
	 */

	public static void Lost() {
		System.out.println("You did not win. Whomp whomp!");

	}

	/**
	 * This will be executed if the player decides to take a hit from the dealer.
	 * @param deck the created deck of cards
	 * @param hand the array of cards in the player's hand
	 *
	 */

	public static void PlayerHit(Deck deck, List<Card> hand) {
		hand.add(deck.draw());
		Card[] dHand = new Card[] {};
		dHand = hand.toArray(dHand);
		int playerHand = 0;
		int pAces = 0;
		for (int i = 0; i < dHand.length; i++) {
			playerHand += dHand[i].getSpecificCard();
			if(dHand[i].getSpecificCard() == 1) {
				pAces++;
			}
			while (pAces >0 && playerHand > 21) {
				playerHand -= 10;
				pAces--;
			}
		}
	}

	/**
	 * This will be executed to check if the user overshot the 21 limit.
	 * @param playerHand the player's hand
	 * @return true if the player's hand has a value over the limit of 21
	 * @return false if the player's hand has a value under the limit of 21
	 *
	 */

	public static boolean NextRound(int playerHand) {
		if(playerHand > 21) {
			System.out.println("I am sorry but you lost this round. Your pair is over the 21 limit.");
			return true;
		}
		return false;
	}


	/**
	 * This is the display message that will be displayed to the player
	 *
	 */

	public static void headerMsg() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------+");
		System.out.println("|                                   Welcome User,                              |");
		System.out.println("|          This application is a simple Black Jack game, which will utilize    |");
		System.out.println("|   the skills, knowledge, and topics discussed up until this point. In order  |");
		System.out.println("|  to play the game you must do the following: set a bet, starting cash level. |");
		System.out.println("| At somepoint during the game you will need to make a decision to either stay |");
		System.out.println("| or take another hit from the Dealer. If you would like to pull another card  |");
		System.out.println("|        enter (h), and if you would like to stay with your hand enter (s).    |");
		System.out.println("|                                    Let's begin!                              |");
		System.out.println("+------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

}
