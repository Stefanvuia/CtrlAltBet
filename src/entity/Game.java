package entity;

public interface Game {
    int sumHand(Player player);

    void addToHand(Player player, Card card);

    boolean userWin();
}
