package use_case.games;
public interface GameDataAccessInterface {
    int getFund(String username);

    void editFund(String username, int amount);
}
