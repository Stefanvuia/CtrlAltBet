package entity.account;

public interface Account {
    void editFunds(int amount);

    String getUsername();

    String getPassword();

    int getFunds();
}
