package Controller;

import View.*;
import Model.*;

import java.awt.event.*;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class SalesController extends SerializedDataCollection implements ActionListener, MouseListener {
    Sales ssv = new Sales();
    NewSales nsv = new NewSales();

    int num = 0;
    int items = 0;
    int quantity = 0;
    int total = 0;

    public SalesController() {
        deSerializeDetails(salesArray, "salesDetails");

        ssv.setVisible(true);

        populateAllView();

        // APPending Action Listeners
        ssv.salesBackButton.addActionListener(this);
        ssv.newSalesButton.addActionListener(this);
        nsv.newSalesAddButton.addActionListener(this);
        nsv.newSalesPayButton.addActionListener(this);
        nsv.newSaleBackButton.addActionListener(this);

    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ssv.salesBackButton) {
            MainController mc = new MainController();
            mc.loginView.setVisible(false);
            mc.nav.setVisible(true);
            ssv.setVisible(false);
        }

        else if (e.getSource() == ssv.newSalesButton) {
            ssv.setVisible(false);
            nsv.setVisible(true);
            nsv.newSalesPayButton.setEnabled(false);
            populateProductName();

        }

        else if (e.getSource() == nsv.newSalesAddButton) {
            num = nsv.newSalesProductNameField.getSelectedIndex();
            int quantityLeft = productArray.get(num).getQuantityLeft();

            if (nsv.newSalesQuantityField.getText().isEmpty()) {
                messageLabel(nsv.messageLabel, "All fields are mandatory", Color.red, Color.white);
                itemRemover(nsv.messageLabel);
            } else if (!(nsv.newSalesQuantityField.getText().matches("[0-9]+"))) {
                messageLabel(nsv.messageLabel, "Price Field expects a number", Color.red, Color.white);
                itemRemover(nsv.messageLabel);
            } else if (Integer.valueOf(nsv.newSalesQuantityField.getText()) > quantityLeft) {
                messageLabel(nsv.messageLabel, "Quantity exceed stock (" + String.valueOf(quantityLeft) + ")",
                        Color.red, Color.white);
                itemRemover(nsv.messageLabel);

            } else {
                deSerializeDetails(productArray, "productsDetails");
                cartArray.add(new ShoppingCart(productArray.get(num).getProductName(),
                        Integer.valueOf(productArray.get(num).getProductPrice()),
                        Integer.valueOf(nsv.newSalesQuantityField.getText()),
                        Integer.valueOf(nsv.newSalesQuantityField.getText())
                                * productArray.get(num).getProductPrice()));

                populateCartTable();
                nsv.newSalesQuantityField.setText("");
                populateTotal();

            }
        } else if (e.getSource() == nsv.newSalesPayButton) {
            salesArray.add(new Sale(productArray.get(num).getProductId(), productArray.get(num).getProductName(),
                    total, quantity));

            serializeDetails(salesArray);

            cartArray.clear();
            nsv.setVisible(false);
            SalesController sc = new SalesController();
        } else if (e.getSource() == nsv.newSaleBackButton) {
            cartArray.clear();
            nsv.setVisible(false);
            SalesController sc = new SalesController();

        }

    }

    public void populateAllView() {
        deSerializeDetails(salesArray, "salesDetails");
        deSerializeIdGenerator();

        ssv.listTableModel = new DefaultTableModel(ssv.rowData, new String[] {
                "Product Id", "Product Name", "Total Amount", "Quantity" });

        salesArray.forEach(sale -> {
            ssv.listTableModel.addRow(
                    new Object[] { sale.getProductId(), sale.getProductName(),
                            sale.getTotalAmount(), sale.getNumSold() });
        });

        ssv.salesDetailsTable.setModel(ssv.listTableModel);
    }

    public void buttonEnabler(boolean setEnabled) {
        ssv.newSalesButton.setEnabled(setEnabled);
    }

    // mouse-event interface override
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == ssv.salesDetailsTable) {
            JTable target = (JTable) e.getSource();

            num = target.getSelectedRow(); // select a row

            buttonEnabler(true);
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

    public void populateProductName() {
        nsv.newSalesProductNameField.removeAllItems();
        deSerializeDetails(productArray, "productsDetails");

        productArray.forEach(product -> {
            nsv.newSalesProductNameField.addItem(product.getProductName());
        });
    }

    public void populateCartTable() {
        nsv.listTableModel = new DefaultTableModel(ssv.rowData, new String[] {
                "Product Name ", "Price", "Quantity", "Total" });

        cartArray.forEach(cartItem -> {
            nsv.listTableModel.addRow(
                    new Object[] { cartItem.getProductName(), cartItem.getProductPrice(),
                            cartItem.getQuantity(), cartItem.getTotal() });
        });

        nsv.cartTable.setModel(nsv.listTableModel);

    }

    public void populateTotal() {
        items = 0;
        quantity = 0;
        total = 0;

        items = cartArray.size();

        cartArray.forEach(cartItem -> {
            quantity += cartItem.getQuantity();

        });

        cartArray.forEach(cartItem -> {
            total += cartItem.getTotal();
        });

        nsv.newSalesTotalItemField.setText(String.valueOf(items));
        nsv.newSalesSubTotalField.setText(String.valueOf(total));
        nsv.newSalesTotalQuantityField.setText(String.valueOf(quantity));
        nsv.newSalesTotalAmountField.setText(String.valueOf(total));

        nsv.newSalesPayButton.setEnabled(true);
    }

}
