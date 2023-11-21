package interface_adapter.blackjack.blackjack_start;

import use_case.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartInputData;

public class BlackJackStartController {
    final BlackJackStartInputBoundary startUseCaseInteractor;

    public BlackJackStartController(BlackJackStartInputBoundary startUseCaseInteractor) {
        this.startUseCaseInteractor = startUseCaseInteractor;
    }

    public void execute(String username, int bet) {
        BlackJackStartInputData startInputData = new BlackJackStartInputData(username, bet);

        startUseCaseInteractor.execute(startInputData);
    }
}
