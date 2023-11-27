package history;

import java.util.ArrayList;

public interface UserHistory {
    String getUsername();

    ArrayList<Double> getPayouts(String game);

    void setPayouts(String game, Double values);

    String payoutFormatter(String game);

}
