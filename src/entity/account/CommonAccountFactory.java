package entity.account;

public class CommonAccountFactory implements AccountFactory {
    @Override
    public Account create(String username, String password) {
        return new CommonAccount(username, password);
    }
}
