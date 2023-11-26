package data_access;

import entity.user.User;
import entity.user.UserFactory;
import use_case.games.GameDataAccessInterface;
import use_case.game_menu.MenuDataAccessInterface;
import use_case.launch_menu.login.LoginUserDataAccessInterface;
import use_case.launch_menu.signup.SignupUserDataAccessInterface;
import use_case.launch_menu.update.UpdateUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements
        SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        UpdateUserDataAccessInterface, GameDataAccessInterface, MenuDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();


    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("balance", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String balanceString = String.valueOf(col[headers.get("balance")]);
                    int balance = Integer.parseInt(balanceString);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password, ldt, balance);
                    accounts.put(username, user);
                }
            }
        }
    }


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime(), user.getBalance());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User requestModel) {
        accounts.put(requestModel.getName(), requestModel);
        this.save();
    }
    public User getUserByName(String username) {
        return accounts.get(username);
    }
    public boolean validatePassword(String username, String password) {
        User user = accounts.get(username);
        return user.getPassword().equals(password);
    }

    @Override
    public int getFund(String username) {
        return accounts.get(username).getBalance();
    }

    @Override
    public void editFund(String username, int amount) {
        accounts.get(username).editBalance(amount);
        save(accounts.get(username));
    }
}
