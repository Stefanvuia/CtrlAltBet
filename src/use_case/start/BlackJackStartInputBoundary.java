package use_case.start;

import java.io.IOException;

public interface BlackJackStartInputBoundary {
    void execute(BlackJackStartInputData blackJackStartData) throws IOException;
}
