package entity.account;

import entity.account.Account;

public class CommonAccount implements Account {
    private final String username;
    private final String password;
    private int funds;
    public CommonAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.funds = 0;
    }
    @Override
    public void editFunds(int amount) {
        this.funds += amount;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getFunds() {
        return this.funds;
    }
}
