# CtrlAltBet
Coding assignment 207 Group#269

# Instructions for the Various Games

## Baccarat:

Baccarat is a card game played with 8 standard 52-card decks that revolves around comparing two hands, the Player and the Banker. In each round of baccarat, players bet on which hand will have a greater value, or if both hands will have the same value. Once the bets are placed, the hands are dealt to each side and the player receives a payout based on their guess.

The value of each hand is calculated in the following way:
- 10 and all face cards have a value of 0
- Aces have a value of 1
- Remaining cards have their numerical value
- The value of the hand is the sum of the hand modulo 10 (e.g., a hand with 9 and 7 has a value of 6)
- The highest possible value is therefore 9

Hands are dealt in the following way:
- Both the Player and the Banker initially receives 2 cards
- If the Player's hand has a value of less than 6, they receive a third card
- If the Player does not receive a third card, the Banker receives a third card if the Banker's hand has a value of less than 6
- If the Player receives a third card, the Banker receives a third card based on the following table, with Y signaling that the Banker receives a third card, and N meaning signaling that the Banker does not receive a third card :

| Player's third card value →<br><br>Value of Banker's hand ↓ 	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	|
|:-----------------------------------------------------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|
|                             < 3                             	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	|
|                              3                              	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| N 	| Y 	|
|                              4                              	| N 	| N 	| Y 	| Y 	| Y 	| Y 	| Y 	| Y 	| N 	| N 	|
|                              5                              	| N 	| N 	| N 	| N 	| Y 	| Y 	| Y 	| Y 	| N 	| N 	|
|                              6                              	| N 	| N 	| N 	| N 	| N 	| N 	| Y 	| Y 	| N 	| N 	|
|                             > 6                             	| N 	| N 	| N 	| N 	| N 	| N 	| N 	| N 	| N 	| N 	|

The payout is calculated in the following way:
- If the player correctly bets on the Player side, the player wins their wager
- If the player correctly bets on the Banker side, the player wins their wager less a 5% commission to the casino (i.e., win 95% of their wager)
- If the player correctly bets on a tie, the player wins 8 times their wager (i.e., pays 8 to 1)
  
## War:

The game is normally played with six standard 52-card decks. The cards are ranked in the same way that cards in poker games are ranked, with aces being the highest cards.[2]

One card each is dealt to a dealer and to a player. If the player's card is higher, they win the wager they bet. However, if the dealer's card is higher, the player loses their bet.[3]

A tie occurs when the dealer and the player each have cards of the same rank. In a tie situation, the player has two options:

The player can surrender, in which case the player loses half the bet.
The player can go to war, in which case the player must double their stake.
If the player continues play in view of a tie, the dealer burns (discards) three cards before dealing each of them an additional card. If the player's card is ranked higher than the dealer's, then the player wins 1.5 times the amount of their original wager only. If the dealer's card is ranked higher than the player's, the player loses their (doubled) wager. If the ranks are equal, then the player wins the amount of their doubled wager.

## BlackJack:

The game is normally played with six standard 52-card decks. 10, Jack, Queen, and King all have a value of 10, numbered cards have a value equal to their number, and ace has a value of 11 or 1 depending on whether or not an 11 would make your sum go over 21.

The game starts with two cards each being dealt to a dealer and a player. Only one of the dealer's cards is visible.

The player has the option to hit or stand. 
- Hit: The dealer adds a card to the player's hand, this can be repeated until the player busts (sum goes over 21) or they choose to stand.
- Stand: No more cards are added and now it's the dealer's turn to hit
  
If the player has an ace and a card with a value of 10 they have blackjack. If the dealer's hand does not sum 21 by the end of their turn the player wins 3/2 of their wager or breaks even otherwise (i.e., pays 3 to 2).
If the player's hand sum is more than the dealers without going over 21, they win their wager.
If the player's hand sum is less than the dealers with going over 21, they win their wager.
If the player's hand sum is more than 21 they lose their wage by default.




