package use_case.game_menu.launch_game;

public class LaunchOutputData {
    private final String username;

    private final int funds;

    private final String game;

    public LaunchOutputData(String username, String game, int funds) {
        this.username = username;
        this.game = game;
        this.funds = funds;
    }

    public String getUsername() {
        return username;
    }

    public int getFunds() {
        return funds;
    }

    public String getGame() {
        return game;
    }
}
