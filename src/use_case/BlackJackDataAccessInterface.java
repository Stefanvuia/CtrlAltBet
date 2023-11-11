package use_case;

import entity.Account;

public interface BlackJackDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);

}
