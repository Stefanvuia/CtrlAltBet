package entity.account;

public interface AccountFactory {
    Account create(String username, String password);
}
