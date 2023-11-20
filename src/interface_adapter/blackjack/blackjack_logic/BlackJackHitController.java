package interface_adapter.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGameInterface;
import use_case.blackjack.blackjack_logic.BlackJackHitInputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackInputGameData;

public class BlackJackHitController {
    final BlackJackHitInputBoundary hitInteractor;

    public BlackJackHitController(BlackJackHitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }

    public void execute(BlackJackGameInterface blackJackGameInterface) {
        hitInteractor.execute(new BlackJackInputGameData(blackJackGameInterface));
    }
}
