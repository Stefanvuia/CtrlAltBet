package entity;

public interface AccountFactory {
    Account create(String username, String password);
}
