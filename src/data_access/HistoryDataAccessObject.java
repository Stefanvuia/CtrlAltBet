package data_access;

import entity.user.CommonUserHistory;
import entity.user.UserHistory;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.account_menu.reset_graph.ResetDataAccessInterface;

import java.io.*;
import java.util.*;

/**
 * A data access object (DAO) for handling user history game data persistence.
 * Implements HistoryDataAccessInterface and ResetDataAccessInterface to provide
 * methods for interacting with user history data.
 */
public class HistoryDataAccessObject implements HistoryDataAccessInterface, ResetDataAccessInterface {

    /**
     * The file used for storing user history data.
     */
    private final File csvFile;

    /**
     * A map for storing the headers of the CSV file with their corresponding column index.
     */
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    /**
     * A map for storing user history objects, keyed by username.
     */
    private final Map<String, UserHistory> accounts = new HashMap<>();

    /**
     * Constructs a new HistoryDataAccessObject using the provided CSV file path.
     * Initializes the headers map and reads existing user data from the file.
     *
     * @param csvPath The path to the CSV file used for data storage.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public HistoryDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("blackjack", 1);
        headers.put("baccarat", 2);
        headers.put("war", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,blackjack,baccarat,war");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String blackjackString = String.valueOf(col[headers.get("blackjack")]);
                    String baccaratString = String.valueOf(col[headers.get("baccarat")]);
                    String warString = String.valueOf(col[headers.get("war")]);

                    UserHistory userHistory = new CommonUserHistory(username);

                    String[] blackjackArray = blackjackString.split("\\s+"); // split by space
                    // Parse each individual blackjack payout into a double and add to ArrayList
                    for (String payout : blackjackArray) {
                        userHistory.setPayouts("blackjack", Double.parseDouble(payout));
                    }
                    String[] baccaratArray = baccaratString.split("\\s+"); // split by space
                    // Parse each individual baccarat payout into a double and add to ArrayList
                    for (String payout : baccaratArray) {
                        userHistory.setPayouts("baccarat", Double.parseDouble(payout));
                    }
                    String[] warArray = warString.split("\\s+"); // split by space
                    // Parse each individual war payout into a double and add to ArrayList
                    for (String payout : warArray) {
                        userHistory.setPayouts("war", Double.parseDouble(payout));
                    }

                    accounts.put(username, userHistory);
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

            for (UserHistory user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getUsername(), user.payoutFormatter("blackjack"), user.payoutFormatter("baccarat"), user.payoutFormatter("war"));
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) { throw new RuntimeException(e); }
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

    /**
     * Adds a new user with the given username to the data storage.
     * Initializes their history for each game with a default value.
     *
     * @param username The username of the user to add.
     */
    @Override
    public void addUser(String username) {
        if (!existsByName(username)) {
            UserHistory userHistory = new CommonUserHistory(username);
            accounts.put(username, userHistory);

            accounts.get(username).setPayouts("blackjack", 0.0);
            accounts.get(username).setPayouts("baccarat", 0.0);
            accounts.get(username).setPayouts("war", 0.0);

            save();
        }
    }

    /**
     * Adds a payout entry for the specified user and game.
     * If the user does not exist, they are added to the data storage.
     *
     * @param username The username of the user.
     * @param game The game for which the payout is being recorded.
     * @param amount The amount of the payout.
     */
    @Override
    public void addPayout(String username, String game, double amount) {
        if (!existsByName(username)){
            addUser(username);
        }
        accounts.get(username).setPayouts(game, amount);
        save();
    }

    /**
     * Retrieves a list of payout amounts for a specific user and game.
     *
     * @param username The username of the user.
     * @param game The game for which the payout history is requested.
     * @return An ArrayList of Double values representing the payout history.
     */
    @Override
    public ArrayList<Double> getPayouts(String username, String game) {
        return accounts.get(username).getPayouts(game);
    }

    /**
     * Resets the history data for a specific user.
     * This involves removing the user's history from the CSV file and from memory.
     *
     * @param username The username of the user whose history is to be reset.
     * @throws IOException If an I/O error occurs during file processing.
     */
    public void reset(String username) throws IOException {
        File inputFile = this.csvFile;
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(username)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) { throw new IOException("Error processing the file", e); }

        if (!inputFile.delete()) { throw new IOException("Could not delete original file"); }

        if (!tempFile.renameTo(inputFile)) { throw new IOException("Could not rename temp file to original file name");}

        accounts.remove(username);
    }

}
