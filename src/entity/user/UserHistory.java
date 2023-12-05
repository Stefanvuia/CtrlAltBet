package entity.user;

import java.util.ArrayList;

/**
 * Interface for managing and accessing a user's game history.
 * This interface defines methods for retrieving and setting the payout history for different games.
 */
public interface UserHistory {
    String getUsername();

    ArrayList<Double> getPayouts(String game);

    void setPayouts(String game, Double values);

    String payoutFormatter(String game);

}
