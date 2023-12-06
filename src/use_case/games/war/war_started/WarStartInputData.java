package use_case.games.war.war_started;

public class WarStartInputData {
    private final String username;
    private final int bet;

    public WarStartInputData(String username, int bet) {
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
