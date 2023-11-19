package interface_adapter.blackjack.blackjack_logic;

import entity.Game;
import use_case.blackjack.blackjack_logic.BlackJackHitInputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackInputGameData;
import use_case.blackjack.blackjack_logic.BlackJackStandInputBoundary;

public class BlackJackStandController {
    final BlackJackStandInputBoundary standInteractor;

    public BlackJackStandController(BlackJackStandInputBoundary standInteractor) {
        this.standInteractor = standInteractor;
    }

    public void execute(Game game) {
        standInteractor.execute(new BlackJackInputGameData(game));
    }
}
