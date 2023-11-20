package use_case.blackjack;
public interface GameDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);
}
