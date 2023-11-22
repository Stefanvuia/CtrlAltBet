package use_case;
public interface GameDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);
}
