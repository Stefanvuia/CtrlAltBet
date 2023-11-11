package use_case;

import entity.BlackjackGame;

import java.io.IOException;

public interface BlackJackHitInputBoundary {
    void execute(BlackJackInputGameData blackJackInputGameData) throws IOException;
}
