package use_case.blackjack;
public interface BlackJackDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);
}
