package entity.game_logic;

import entity.Card;

import java.util.List;

public class BlackJackGame implements BlackJackGameInterface {
    private final Player user;
    private final Player dealer;
    private final String deckId;

    public BlackJackGame(Player user, Player dealer, String deckId){
        this.user = user;
        this.dealer = dealer;
        this.deckId = deckId;
    }
    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int aces = 0;
        int sum = 0;
        for (Card card : hand) {
            if (card.getValue().equals("ACE") && aces > 0) {
                aces++;
                sum++;
            } else if (card.getValue().equals("ACE")) {
                aces++;
                sum += 11;
            } else if (
                    card.getValue().equals("JACK") || card.getValue().equals("QUEEN") || card.getValue().equals("KING")
            ) {
                sum += 10;
            } else {
                sum += Integer.parseInt(card.getValue());
            }
        }

        if (sum > 21 && aces > 0) {
            return (sum - 10);
        } else {
            return sum;
        }
    }

    @Override
    public void addToHand(Player player, Card card) {
        player.addToHand(card);
    }

    @Override
    public void addToHand(Player player, List<Card> cards) {
        for (Card card : cards) {
            player.addToHand(card);
        }
    }

    @Override
    public boolean userWin() {
        return (sumHand(user) > sumHand(dealer) && sumHand(user) <= 21) || sumHand(dealer) > 21;
    }

    @Override
    public Player getPlayer() {
        return user;
    }

    @Override
    public Player getDealer() {
        return dealer;
    }

    @Override
    public String getDeck() {
        return deckId;
    }
}
