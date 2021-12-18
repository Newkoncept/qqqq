package Controller;

import java.awt.event.*;
import java.awt.Color;

import javax.swing.JLabel;

import View.*;

public class MainController extends SerializedDataCollection implements ActionListener {
    LoginView loginView = new LoginView();
    Navigator nav = new Navigator();

    // Error tracker
    private int error_tracker;

    public MainController() {

        loginView.setVisible(true);

        // Deserializing the details of faculty members
        deSerializeDetails(usersArray, "usersDetails");

        // Appending Action Listeners
        loginView.loginButton.addActionListener(this);
        nav.inventoryButton.addActionListener(this);
        nav.productButton.addActionListener(this);
        nav.usersButton.addActionListener(this);
        nav.salesButton.addActionListener(this);
    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        error_tracker = 0;

        for (int i = 0; i < usersArray.size(); i++) {
            // Confirming username existence using the error tracker variable
            if (!(usersArray.get(i).getUserId().equals(loginView.userIdField.getText()))) {
                error_tracker++;
            }

            // Confirming validity of username and password using the error tracker variable
            if (usersArray.get(i).getPassword().equals(loginView.passwordField.getText())) {
                String pwd = String.valueOf(loginView.passwordField.getText());
                String dbPwd = usersArray.get(i).getPassword();
                if (!(dbPwd.equals(pwd))) {
                    error_tracker++;
                }
            }

        }

        // Login Button logic controller
        if (e.getSource() == loginView.loginButton) {
            // Validating to check for empty fields
            if (loginView.userIdField.getText().isEmpty() || loginView.passwordField.getText().isEmpty()) {
                messageLabel(loginView.messageLabel, "All fields are mandatory", Color.red, Color.white);
                itemRemover(loginView.messageLabel);
            }

            // Displaying error if username or password as mismatch
            else if (error_tracker > 0) {
                messageLabel(loginView.messageLabel, "Incorrect Details", Color.red, Color.white);
                itemRemover(loginView.messageLabel);
            }

            // Logging the user into the system
            else {
                loginView.setVisible(false);
                nav.setVisible(true);
            }
        }

        else if (e.getSource() == nav.inventoryButton) {
            nav.setVisible(false);
            InventoryController ic = new InventoryController();
        }

        else if (e.getSource() == nav.productButton) {
            ProductController pc = new ProductController();
            nav.setVisible(false);
        }

        else if (e.getSource() == nav.usersButton) {
            UsersController uc = new UsersController();
            nav.setVisible(false);
        }

        else if (e.getSource() == nav.salesButton) {
            SalesController sc = new SalesController();
            nav.setVisible(false);
        }

    }

    // Method to remove the message label after specified time
    public void itemRemover(JLabel label) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                label.setVisible(false);
            }
        }, 3000);
    }

    // Method to show either success message or error message
    public void messageLabel(JLabel label, String message, Color bgColor, Color textColor) {
        label.setText(message);
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setForeground(textColor);
        label.setVisible(true);
    }
}
