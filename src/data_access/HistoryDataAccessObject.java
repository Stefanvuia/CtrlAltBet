package data_access;

import entity.user.CommonUserHistory;
import entity.user.UserHistory;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.account_menu.reset_graph.ResetDataAccessInterface;

import java.io.*;
import java.util.*;

public class HistoryDataAccessObject implements HistoryDataAccessInterface, ResetDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, UserHistory> accounts = new HashMap<>();

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

    @Override
    public void addPayout(String username, String game, double amount) {
        if (!existsByName(username)){
            addUser(username);
        }
        accounts.get(username).setPayouts(game, amount);
        save();
    }

    @Override
    public ArrayList<Double> getPayouts(String username, String game) {
        return accounts.get(username).getPayouts(game);
    }

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
        } catch (IOException e) {
            throw new IOException("Error processing the file", e);
        }

        if (!inputFile.delete()) {
            throw new IOException("Could not delete original file");
        }

        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Could not rename temp file to original file name");
        }

        accounts.remove(username);
    }

}
