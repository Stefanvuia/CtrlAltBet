package users.login;

// Login input data
public class LoginInputData {

    final private String name;
    final private String password;

    public LoginInputData(String name, String password) {
        this.name = name;
        this.password = password;
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

}
