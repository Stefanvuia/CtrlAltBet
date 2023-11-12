package use_case;
public interface BlackJackDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);
}
