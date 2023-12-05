package entity.user;

import java.time.LocalDateTime;

/**
 * Represents a common user with basic information such as name, password, creation time, and balance.
 */
class CommonUser implements User {

    /**
     * Constructs a CommonUser with the specified parameters.
     * name         The name of the user.
     * password     The password of the user.
     * creationTime The time when the user was created.
     * balance      The initial balance of the user.
     */
    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private int balance;


    /**
     * Checks if the user's password is valid.
     * return true if the password is valid (non-null and length > 5), {@code false} otherwise.
     */
    public CommonUser(String name, String password, LocalDateTime creationTime, int balance) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.balance = balance;
    }


    /**
     * Checks if the user's password is valid.
     *
     * @return {@code true} if the password is valid (non-null and length > 5), {@code false} otherwise.
     */
    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }


    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * Gets the creation time of the user.
     *
     * @return The creation time of the user.
     */
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }


    /**
     * Gets the current balance of the user.
     *
     * @return The current balance of the user.
     */
    @Override
    public int getBalance() {
        return balance;
    }


    /**
     * Edits the balance of the user by adding the specified amount.
     *
     * @param balance The amount to be added to the user's balance.
     */
    @Override
    public void editBalance(int balance) {
        this.balance += balance;
    }

}
