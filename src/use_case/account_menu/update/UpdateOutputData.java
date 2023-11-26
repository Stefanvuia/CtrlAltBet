package use_case.account_menu.update;


import entity.user.User;

// Sign up response body
public class UpdateOutputData {

    private final int newFunds;

    public UpdateOutputData(int newFunds) {
        this.newFunds = newFunds;
    }

    public int getFunds() {
        return newFunds;
    }

}
