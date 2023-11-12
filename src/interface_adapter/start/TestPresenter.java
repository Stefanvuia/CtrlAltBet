package interface_adapter.start;

import entity.Game;
import use_case.start.BlackJackStartOutputBoundary;
import use_case.start.BlackJackStartOutputData;

public class TestPresenter implements BlackJackStartOutputBoundary {

    private final TestViewModel testViewModel;

    public TestViewModel getTestViewModel() {
        return this.testViewModel;
    }

    public TestPresenter(TestViewModel testViewModel){
        this.testViewModel = testViewModel;
    }
    @Override
    public void prepareSuccessView(BlackJackStartOutputData outputData) {
        Game game = outputData.getGame();
        testViewModel.getState().setGame(game);
        System.out.println("Player hand: "
                        + game.getPlayer().getHand().get(0).getValue()
                        + " "
                        + game.getPlayer().getHand().get(1).getValue()
                        + " Sum: "
                        + game.sumHand(game.getPlayer())
        );

        System.out.println("Dealer hand: " + game.getDealer().getHand().get(0).getValue());
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
