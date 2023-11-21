package users.signup;

public class SignupInputData {

    final private String name;
    final private String password;
    final private String repeatPassword;

    public SignupInputData(String name, String password, String repeatPassword) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
