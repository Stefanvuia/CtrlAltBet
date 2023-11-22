package use_case.menu.launch_game;

public class LaunchInputData {
    private final String username;

    private final String game;

    public LaunchInputData(String username, String game) {
        this.username = username;
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public String getGame() {
        return game;
    }
}
