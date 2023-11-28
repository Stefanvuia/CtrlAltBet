package entity.user;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private int balance;

    public CommonUser(String name, String password, LocalDateTime creationTime, int balance) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.balance = balance;
    }

    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void editBalance(int balance) {
        this.balance += balance;
    }

}
