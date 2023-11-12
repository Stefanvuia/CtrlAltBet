package use_case.start;

public class BlackJackStartInputData {
    private String username;
    private int bet;

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
