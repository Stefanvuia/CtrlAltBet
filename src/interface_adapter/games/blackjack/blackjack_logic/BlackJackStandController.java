package interface_adapter.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGameInterface;
import use_case.games.blackjack.blackjack_logic.BlackJackInputGameData;
import use_case.games.blackjack.blackjack_logic.BlackJackStandInputBoundary;

public class BlackJackStandController {
    final BlackJackStandInputBoundary standInteractor;

    public BlackJackStandController(BlackJackStandInputBoundary standInteractor) {
        this.standInteractor = standInteractor;
    }

    public void execute(BlackJackGameInterface blackJackGameInterface) {
        standInteractor.execute(new BlackJackInputGameData(blackJackGameInterface));
    }
}
