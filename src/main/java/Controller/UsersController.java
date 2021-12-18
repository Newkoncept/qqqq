package Controller;

import View.*;

import java.awt.event.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Person;

public class UsersController extends SerializedDataCollection implements ActionListener, MouseListener {
    Users uuv = new Users();
    int num = 0;

    public UsersController() {
        deSerializeDetails(usersArray, "usersDetails");

        userIdGenerator();

        uuv.setVisible(true);

        populateAllView();

        uuv.userInsertButton.addActionListener(this);
        uuv.userUpdateButton.addActionListener(this);
        uuv.userDeleteButton.addActionListener(this);
        uuv.userBackButton.addActionListener(this);
        uuv.usersDetailsTable.addMouseListener(this);

    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uuv.userInsertButton) {
            if (uuv.userIdField.getText().isEmpty() || uuv.userFirstNameField.getText().isEmpty()
                    || uuv.userLastNameField.getText().isEmpty() || uuv.userEmailField.getText().isEmpty()) {
                messageLabel(uuv.messageLabel, "All fields are mandatory", Color.red, Color.white);
                itemRemover(uuv.messageLabel);
            } // Checking the email field for an @ sign
            else if (!(uuv.userEmailField.getText().contains("@"))) {
                messageLabel(uuv.messageLabel, "Email Field must contain '@'", Color.red, Color.white);
                itemRemover(uuv.messageLabel);
            } else {
                usersArray.add(new Person(uuv.userIdField.getText(), uuv.userFirstNameField.getText(),
                        uuv.userLastNameField.getText(), uuv.userEmailField.getText(), "12345", "2021-01-14"));

                idGenerator[1]++;
                serializeDetails(usersArray);
                serializeIdGenerator();

                populateAllView();
                inputReset();
                userIdGenerator();

            }
        }

        else if (e.getSource() == uuv.userUpdateButton) {
            usersArray.get(num).setFirstName(uuv.userFirstNameField.getText());
            usersArray.get(num).setLastName(uuv.userLastNameField.getText());
            usersArray.get(num).setEmailAddress(uuv.userEmailField.getText());

            populateAllView();
            inputReset();
            userIdGenerator();
            buttonEnabler(true, false);
        }

        else if (e.getSource() == uuv.userDeleteButton) {
            usersArray.remove(num);

            populateAllView();
            inputReset();
            userIdGenerator();
            buttonEnabler(true, false);
        }

        else if (e.getSource() == uuv.userBackButton) {
            MainController mc = new MainController();
            mc.loginView.setVisible(false);
            mc.nav.setVisible(true);
            uuv.setVisible(false);
        }

    }

    public void populateAllView() {
        uuv.listTableModel = new DefaultTableModel(uuv.rowData, new String[] {
                "User Id", "First Name", "Last Name", "Email Address" });

        usersArray.forEach(user -> {
            uuv.listTableModel.addRow(
                    new Object[] { user.getUserId(), user.getFirstName(), user.getLastName(),
                            user.getEmailAddress(), user.getCreatedDate() });
        });

        uuv.usersDetailsTable.setModel(uuv.listTableModel);

        buttonEnabler(true, false);
    }

    public void buttonEnabler(boolean setEnabled, boolean setDisabled) {
        uuv.userInsertButton.setEnabled(setEnabled);
        uuv.userDeleteButton.setEnabled(setDisabled);
        uuv.userUpdateButton.setEnabled(setDisabled);
    }

    public void inputReset() {
        uuv.userIdField.setText("");
        uuv.userFirstNameField.setText("");
        uuv.userLastNameField.setText("");
        uuv.userEmailField.setText("");
    }

    public void populateInput() {
        uuv.userIdField.setText(usersArray.get(num).getUserId());
        uuv.userFirstNameField.setText(usersArray.get(num).getFirstName());
        uuv.userLastNameField.setText(usersArray.get(num).getLastName());
        uuv.userEmailField.setText(usersArray.get(num).getEmailAddress());
    }

    // mouse-event interface override
    @Override
    public void mouseClicked(MouseEvent e) {
        // arrayLength = staffArray.size();

        if (e.getSource() == uuv.usersDetailsTable) {
            JTable target = (JTable) e.getSource();

            num = target.getSelectedRow(); // select a row

            buttonEnabler(false, true);

            populateInput();

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void itemRemover(JLabel label) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                label.setVisible(false);
            }
        }, 3000);
    }

    public void messageLabel(JLabel label, String message, Color bgColor, Color textColor) {
        label.setText(message);
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setForeground(textColor);
        label.setVisible(true);
    }

    public void userIdGenerator() {
        String id = "user-" + idGenerator[1];

        uuv.userIdField.setText(id);
    }

}
