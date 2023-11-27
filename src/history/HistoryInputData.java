package history;

public class HistoryInputData {

    private String game;
    private String username;

    public HistoryInputData(String username, String game) {
        this.game = game;
        this.username = username;
    }

    String getGame() {
        return game;
    }

    String getUsername() {
        return username;
    }
}
