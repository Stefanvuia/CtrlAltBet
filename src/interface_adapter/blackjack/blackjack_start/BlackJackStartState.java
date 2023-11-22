package interface_adapter.blackjack.blackjack_start;

public class BlackJackStartState {
    private String username = "cakev";

    private int bet = 0;

    private String betError = null;

    private int funds = 5000;

    public BlackJackStartState(BlackJackStartState copy) {
        username = copy.username;
        bet = copy.bet;
        funds = copy.funds;
        betError = copy.betError;
    }

    public BlackJackStartState() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getBetError() {
        return betError;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }
}
