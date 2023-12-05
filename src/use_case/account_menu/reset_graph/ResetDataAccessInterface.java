package use_case.account_menu.reset_graph;

import java.io.IOException;

public interface ResetDataAccessInterface {
    void reset(String username) throws IOException;
}
