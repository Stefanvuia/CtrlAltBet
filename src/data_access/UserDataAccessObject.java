package data_access;

import entity.Account;
import entity.AccountFactory;
import entity.CommonAccountFactory;
import use_case.BlackJackDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class UserDataAccessObject implements BlackJackDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private AccountFactory accountFactory;

    public UserDataAccessObject(String csvPath, AccountFactory accountFactory) throws IOException {
        this.accountFactory = accountFactory;
        this.csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("funds", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,funds");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String funds = String.valueOf(col[headers.get("funds")]);
                    Account account = accountFactory.create(username, password);
                    account.editFunds(Integer.parseInt(funds));
                    accounts.put(username, account);
                }
            }
        }
    }

//    @Override
    public void save(Account account) {
        accounts.put(account.getUsername(), account);
        this.save();
    }

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }
    
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Account account : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        account.getUsername(), account.getPassword(), account.getFunds());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getFund(String username) {
        return accounts.get(username).getFunds();
    }

    @Override
    public void editFund(String username, int amount) {
        accounts.get(username).editFunds(amount);
    }

    public static void main(String[] args) {
        AccountFactory accountFactory = new CommonAccountFactory();
        UserDataAccessObject userDataAccessObject;
        try{
            userDataAccessObject = new UserDataAccessObject("./users.csv", accountFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Account a = accountFactory.create("cakev", "ilovemen");
        userDataAccessObject.save(a);
        System.out.println(userDataAccessObject.existsByName("cakev"));
        System.out.println(userDataAccessObject.getFund("cakev"));
        userDataAccessObject.editFund("cakev", 3000);
        System.out.println(userDataAccessObject.getFund("cakev"));
        Account b = accountFactory.create("stefa", "compulsivegambling<3");
        userDataAccessObject.save(b);
        System.out.println(userDataAccessObject.getFund("stefa"));
    }
}