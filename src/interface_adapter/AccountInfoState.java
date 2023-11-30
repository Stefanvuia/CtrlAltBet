package interface_adapter;

public class AccountInfoState {
    private String username = "";

    private int funds = 0;

    public int getChange() {
        return change;
    }

    private int change = 0;

    private String errorMessage = null;

    public void setChange(int change) {
        this.change = change;
    }

    public AccountInfoState() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
