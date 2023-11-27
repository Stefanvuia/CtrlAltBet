package use_case.launch_menu.buttons;

public class UserButtonsInputData {
    private String newView = "";

    public UserButtonsInputData(String newView) {
        this.newView = newView;
    }

    public UserButtonsInputData() {}

    public String getNewView() {
        return newView;
    }
}
