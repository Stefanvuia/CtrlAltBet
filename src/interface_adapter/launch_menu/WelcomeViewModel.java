package interface_adapter.launch_menu;

/**
 * ViewModel class representing the state and behavior of the welcome view in the launch menu.
 */
public class WelcomeViewModel {

    /**
     * The label for the login button.
     */
    public final String LOGIN_LABEL = "log in";

    /**
     * The label for the sign-up button.
     */
    public final String SIGN_UP_LABEL = "sign up";

    /**
     * The name of the welcome view.
     */
    private final String viewName = "welcome";

    /**
     * Gets the name of the welcome view.
     *
     * @return The name of the welcome view.
     */
    public String getViewName() {
        return viewName;
    }
}