# CtrlAltBet
Coding assignment 207 Group#269

Domain:
-
Card Games

The general purpose of our software will be to allow users to play classic casino card games.

User Stories:
-

Blackjack (Use Case): Emma decides to play blackjack. Before the round starts Emma wants to be able to place bet using virtual chips. After receiving initial cards, Emma decides whether to “Hit” or “Stand” based on the value of her cards, and after each decision Emma sees the the sum of her cards, if she finished closer to 21 than the dealer without going over wins back twice the money she put in.

Specifications:
-
- In the blackjack option, the user will wager their funds in blackjack against an automated dealer following standard blackjack rules

Entities:
-
- Account:
  - name: String
  - password: String
  - funds: double
  + addFunds(): void
  + takeFunds(): void

- Deck:
  - deckid: String
  - remaining: int
  + shuffle(): void
  + draw(n: int): List<Card>
  + getId(): String
 
- Card:
  - code: String
  - value: int
  - suit: String
 
- Game:
  - player: Account
  - bet: Double
  + startGame(): void
  + payout(bet: double): void

- BlackjackGame:
  - hit():



