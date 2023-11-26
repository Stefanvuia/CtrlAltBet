package use_case.launch_menu.buttons;

public class UserButtonsOutputData {
    private final String newView;

    public UserButtonsOutputData(String newView) {
        this.newView = newView;
    }

    public String getNewView() {
        return newView;
    }
}
