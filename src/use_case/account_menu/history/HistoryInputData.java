package use_case.account_menu.history;

public class HistoryInputData {

    private final String game;
    private final String username;

    public HistoryInputData(String username, String game) {
        this.game = game;
        this.username = username;
    }

    public String getGame() {
        return game;
    }

    public String getUsername() {
        return username;
    }
}
