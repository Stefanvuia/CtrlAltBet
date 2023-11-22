package use_case;

public interface GameHistoryDataAccessInterface {
    void addPayout(String game, double amount);

    double[] getPayouts(String username, String game);
}
