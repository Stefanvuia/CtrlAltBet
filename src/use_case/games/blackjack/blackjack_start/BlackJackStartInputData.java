package use_case.games.blackjack.blackjack_start;

public class BlackJackStartInputData {
    private final String username;
    private final int bet;

    public BlackJackStartInputData(String username, int bet) {
        this.username = username;
        this.bet = bet;
    }

    public String getUsername() {
        return this.username;
    }

    public int getBet() {
        return this.bet;
    }
}
