import app.Main;
import org.junit.jupiter.api.*;
import view.AccountInfoView;
import view.MainMenuView;
import view.baccarat.BaccaratStartView;
import view.blackjack.BlackJackIngameView;
import view.blackjack.BlackJackStartView;
import view.launch_menu.LoginView;
import view.launch_menu.SignupView;
import view.launch_menu.WelcomeView;
import view.war.WarStartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {
    private String message = "";
    private boolean popUpDiscovered = false;

    private JPanel jp;

    private void getApp() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assert app != null;
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        this.jp = (JPanel) ((JPanel) cp).getComponent(0);
    }

    @BeforeEach
    public void setUp() {
        jp = null;
        message = "";
        popUpDiscovered = false;
        try {
            Main.main(null);
        } catch (IOException e) {
            Assertions.fail("Cannot create app");
        }
        getApp();
    }

    @AfterEach
    public void close() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();
        }
    }

    @AfterAll
    public static void reset() {
        try {
            deleteLine();
        } catch (IOException e) {
            Assertions.fail("Cannot clear line");
        }
    }

    @Test
    @Order(1)
    public void signUpLoginTest() {
        WelcomeView wv = (WelcomeView) jp.getComponent(0);
        JPanel buttons = (JPanel) wv.getComponent(0);

        JButton signUpButton = (JButton) buttons.getComponent(1);
        signUpButton.doClick();

        SignupView sv = (SignupView) jp.getComponent(1);
        JTextField usernameField = (JTextField) sv.getComponent(4);
        JTextField passField = (JTextField) sv.getComponent(5);
        JTextField rPasswordField = (JTextField) sv.getComponent(6);
        signUpButton = (JButton) sv.getComponent(7);

        usernameField.setText("kevinin");
        passField.setText("qwerty");
        rPasswordField.setText("asdfgh");
        createCloseTimer(250).start();

        signUpButton.doClick();

        assert (popUpDiscovered);
        Assertions.assertEquals("Passwords do not match", message);

        usernameField.setText("kevinin");
        passField.setText("qwerty");
        rPasswordField.setText("qwerty");

        createCloseTimer(250).start();
        signUpButton.doClick();
        assert !sv.isVisible();

        LoginView lv = (LoginView) jp.getComponent(2);
        ((JTextField) lv.getComponent(3)).setText("kevinin");
        ((JTextField) lv.getComponent(4)).setText("qwerty");
        ((JButton) lv.getComponent(5)).doClick();
        assert !lv.isVisible();
    }

    @Test
    @Order(2)
    public void baccaratTest() {
        login();

        MainMenuView mv = (MainMenuView) jp.getComponent(3);
        assert mv.isVisible();

        ((JButton) mv.getComponent(1)).doClick();
        assert !mv.isVisible();

        BaccaratStartView bv = (BaccaratStartView) jp.getComponent(6);
        createCloseTimer(2500).start();
        ((JButton) bv.getComponent(5)).doClick();
        assert (popUpDiscovered);
    }

    @Test
    @Order(3)
    public void blackJackStartAndStandTest() {
        login();

        MainMenuView mv = (MainMenuView) jp.getComponent(3);
        assert mv.isVisible();

        ((JButton) mv.getComponent(0)).doClick();
        assert !mv.isVisible();

        BlackJackStartView bv = (BlackJackStartView) jp.getComponent(4);
        ((JButton) bv.getComponent(3)).doClick();
        assert !bv.isVisible();

        BlackJackIngameView bgv = (BlackJackIngameView) jp.getComponent(5);
        assert bgv.isVisible();

        createCloseTimer(1000).start();
        ((JButton) bgv.getComponent(2)).doClick();
        assert (popUpDiscovered);
    }

    @Test
    @Order(4)
    public void warStartTest() {
        login();

        MainMenuView mv = (MainMenuView) jp.getComponent(3);
        assert mv.isVisible();

        ((JButton) mv.getComponent(3)).doClick();
        assert !mv.isVisible();

        WarStartView wv = (WarStartView) jp.getComponent(8);
        assert wv.isVisible();

        createCloseTimer(2500).start();
        ((JButton) wv.getComponent(3)).doClick();
        assert (wv.isVisible() && popUpDiscovered) || jp.getComponent(10).isVisible();
    }

    @Test
    @Order(5)
    public void accountPageTest() {
        login();
        MainMenuView mv = (MainMenuView) jp.getComponent(3);
        assert mv.isVisible();
        ((JButton) mv.getComponent(2)).doClick();
        assert !mv.isVisible();
        AccountInfoView av = (AccountInfoView) jp.getComponent(11);
        assert av.isVisible();

        // Check username
        JLabel usernameLabel = (JLabel) ((JPanel) av.getComponent(1)) .getComponent(0);
        Assertions.assertEquals("current user: kevinin", usernameLabel.getText());

        // Deposit test
        JPanel depositPanel = (JPanel) av.getComponent(3);
        ((JFormattedTextField) depositPanel.getComponent(0)).setValue(500);
        createCloseTimer(250).start();
        ((JButton) depositPanel.getComponent(1)).doClick();
        assert popUpDiscovered;
        Assertions.assertEquals("Successfully edited funds!", message);
        // Check new funds
        JLabel fundsLabel = (JLabel) ((JPanel) av.getComponent(2)) .getComponent(0);
        Assertions.assertEquals("current funds: 1000", fundsLabel.getText());

        // Withdraw test
        JPanel withdrawPanel = (JPanel) av.getComponent(4);
        ((JFormattedTextField) withdrawPanel.getComponent(0)).setValue(1000);
        createCloseTimer(250).start();
        ((JButton) withdrawPanel.getComponent(1)).doClick();
        assert popUpDiscovered;
        Assertions.assertEquals("Successfully edited funds!", message);
        // Check new funds
        fundsLabel = (JLabel) ((JPanel) av.getComponent(2)) .getComponent(0);
        Assertions.assertEquals("current funds: 0", fundsLabel.getText());

        ((JButton) av.getComponent(6)).doClick();
        assert jp.getComponent(0).isVisible();
    }

    private void login() {
        // ONLY TO BE CALLED AFTER THE FIRST TEST
        WelcomeView wv = (WelcomeView) jp.getComponent(0);
        JPanel buttons = (JPanel) wv.getComponent(0);
        ((JButton) buttons.getComponent(0)).doClick();
        LoginView lv = (LoginView) jp.getComponent(2);
        ((JTextField) lv.getComponent(3)).setText("kevinin");
        ((JTextField) lv.getComponent(4)).setText("qwerty");
        ((JButton) lv.getComponent(5)).doClick();
    }

    private Timer createCloseTimer(int milliseconds) {
        ActionListener close = e -> {

            Window[] windows = Window.getWindows();
            for (Window window : windows) {

                if (window instanceof JDialog) {
                    JDialog dialog = (JDialog) window;

                    if (dialog.isVisible()) {
                        message = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                        popUpDiscovered = true;
                        window.dispose();
                    }
                }
            }
        };

        Timer t = new Timer(milliseconds, close);
        t.setRepeats(false);
        return t;
    }

    private static void deleteLine() throws IOException {
        // Deletes the temporarily added user
        File file = new File("users.csv");
        File tempFile = new File("tempUsers.csv");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String row;
        while ((row = reader.readLine()) != null) {
            String[] col = row.split(",");
            if (!col[0].equals("kevinin")) {
                writer.write(row);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();
        if (!file.delete()) {
            System.out.println("Could not delete file");
        }
        tempFile.renameTo(file);
    }
}