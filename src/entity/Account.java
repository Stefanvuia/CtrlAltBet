package entity;

public interface Account {
    void editFunds(int amount);

    String getUsername();

    String getPassword();

    int getFunds();
}
