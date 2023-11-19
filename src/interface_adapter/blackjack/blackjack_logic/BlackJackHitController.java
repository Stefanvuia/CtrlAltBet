package interface_adapter.blackjack.blackjack_logic;

import entity.Game;
import use_case.blackjack.blackjack_logic.BlackJackHitInputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackInputGameData;
import use_case.blackjack.blackjack_logic.BlackJackStandInputBoundary;

public class BlackJackHitController {
    final BlackJackHitInputBoundary hitInteractor;

    public BlackJackHitController(BlackJackHitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }

    public void execute(Game game) {
        hitInteractor.execute(new BlackJackInputGameData(game));
    }
}
