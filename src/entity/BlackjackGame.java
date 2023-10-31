package entity;

public class BlackjackGame implements Game {
    Player user;
    Player dealer;


    @Override
    public int sumHand(Player player) {
        return 0;
    }

    @Override
    public void addToHand(Player player, Card card) {
        player.addToHand(card);
    }

    @Override
    public boolean userWin() {
        return (sumHand(user) > sumHand(dealer) && sumHand(user) <= 21) || sumHand(dealer) > 21;
    }
}
