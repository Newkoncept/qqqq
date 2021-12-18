/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.*;
import Model.*;

import java.awt.event.*;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oluwagbemiga
 */

public class ProductController extends SerializedDataCollection implements ActionListener, MouseListener {

    Products ppv = new Products();
    int num = 0;

    public ProductController() {

        deSerializeDetails(productArray, "productsDetails");

        productIdGenerator();

        ppv.setVisible(true);

        populateAllView();

        // APPending Action Listeners
        ppv.productsBackButton.addActionListener(this);
        ppv.productInsertButton.addActionListener(this);
        ppv.productUpdateButton.addActionListener(this);
        ppv.productDeleteButton.addActionListener(this);
        ppv.productDetailsTable.addMouseListener(this);

    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ppv.productInsertButton) {
            if (ppv.productIdField.getText().isEmpty() || ppv.productNameField.getText().isEmpty()
                    || ppv.productPriceField.getText().isEmpty()) {
                messageLabel(ppv.messageLabel, "All fields are mandatory", Color.red, Color.white);
                itemRemover(ppv.messageLabel);
            } else if (!(ppv.productPriceField.getText().matches("[0-9]+"))) {
                messageLabel(ppv.messageLabel, "Price Field expects a number", Color.red, Color.white);
                itemRemover(ppv.messageLabel);
            } else {
                productArray.add(new Item(ppv.productIdField.getText(), ppv.productNameField.getText(),
                        Integer.valueOf(ppv.productPriceField.getText()), 0));

                idGenerator[0]++;
                serializeDetails(productArray);
                serializeIdGenerator();

                populateAllView();
                inputReset();
                productIdGenerator();
            }

        }

        else if (e.getSource() == ppv.productUpdateButton) {
            productArray.get(num).setProductId(ppv.productIdField.getText());
            productArray.get(num).setProductName(ppv.productNameField.getText());
            productArray.get(num).setProductPrice(Integer.valueOf(ppv.productPriceField.getText()));

            populateAllView();
            inputReset();
            buttonEnabler(true, false);
        }

        else if (e.getSource() == ppv.productDeleteButton) {
            productArray.remove(num);

            populateAllView();
            inputReset();
            productIdGenerator();

            buttonEnabler(true, false);
        }

        else if (e.getSource() == ppv.productsBackButton) {
            MainController mc = new MainController();
            mc.loginView.setVisible(false);
            mc.nav.setVisible(true);
            ppv.setVisible(false);
        }

    }

    public void populateAllView() {
        deSerializeDetails(productArray, "productsDetails");
        deSerializeIdGenerator();
        productIdGenerator();

        ppv.listTableModel = new DefaultTableModel(ppv.rowData, new String[] {
                "Product Id", "Product Name ", "Product Price" });

        productArray.forEach(product -> {
            ppv.listTableModel.addRow(
                    new Object[] { product.getProductId(), product.getProductName(),
                            product.getProductPrice() });
        });

        ppv.productDetailsTable.setModel(ppv.listTableModel);

        buttonEnabler(true, false);
    }

    public void buttonEnabler(boolean setEnabled, boolean setDisabled) {
        ppv.productInsertButton.setEnabled(setEnabled);
        ppv.productDeleteButton.setEnabled(setDisabled);
        ppv.productUpdateButton.setEnabled(setDisabled);
    }

    public void inputReset() {
        ppv.productIdField.setText("");
        ppv.productNameField.setText("");
        ppv.productPriceField.setText("");
    }

    public void populateInput() {
        ppv.productIdField.setText(productArray.get(num).getProductId());
        ppv.productNameField.setText(productArray.get(num).getProductName());
        ppv.productPriceField.setText(String.valueOf(productArray.get(num).getProductPrice()));
    }

    // mouse-event interface override
    @Override
    public void mouseClicked(MouseEvent e) {
        // arrayLength = staffArray.size();

        if (e.getSource() == ppv.productDetailsTable) {
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

    public void productIdGenerator() {
        String id = "Prod-" + idGenerator[0];

        ppv.productIdField.setText(id);
    }

}
