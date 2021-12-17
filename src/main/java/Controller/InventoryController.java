package Controller;

import View.*;

import java.awt.event.*;

import java.awt.Color;

import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.*;

public class InventoryController extends SerializedDataCollection implements ActionListener, MouseListener {

    Inventory iiv = new Inventory();
    InventoryUpdate ivi = new InventoryUpdate();

    int num = 0;

    public InventoryController() {

        deSerializeDetails(productArray, "productsDetails");

        iiv.setVisible(true);
        buttonEnabler(false);

        populateAllView();

        // APPending Action Listeners
        iiv.inventoryUpdateButton.addActionListener(this);
        iiv.inventoryDetailsTable.addMouseListener(this);
        iiv.inventoryBackButton.addActionListener(this);
        ivi.inventoryProductUpdateButton.addActionListener(this);
        ivi.inventoryUpdateBackButton.addActionListener(this);
    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iiv.inventoryUpdateButton) {
            iiv.setVisible(false);

            ivi.inventoryUpdateProductNameField.setText(productArray.get(num).getProductName());
            ivi.setVisible(true);
        } else if (e.getSource() == ivi.inventoryProductUpdateButton) {
            if (ivi.inventoryUpdateProductQuantityField.getText().isEmpty()) {
                messageLabel(ivi.messageLabel, "All fields are mandatory", Color.red, Color.white);
                itemRemover(ivi.messageLabel);
            } else {
                productArray.get(num)
                        .setQuantityLeft(productArray.get(num).getQuantityLeft()
                                + Integer.valueOf(ivi.inventoryUpdateProductQuantityField.getText()));
                ivi.inventoryUpdateProductQuantityField.setText("");

                serializeDetails(productArray);
                iiv.setVisible(true);
                ivi.setVisible(false);

                populateAllView();
            }
        } else if (e.getSource() == ivi.inventoryUpdateBackButton) {
            ivi.inventoryUpdateProductQuantityField.setText("");
            iiv.setVisible(true);
            ivi.setVisible(false);

            populateAllView();
        }

        else if (e.getSource() == iiv.inventoryBackButton) {
            Navigator nv = new Navigator();
            iiv.setVisible(false);
        }

    }

    public void buttonEnabler(boolean setEnabled) {
        iiv.inventoryUpdateButton.setEnabled(setEnabled);
    }

    public void populateAllView() {
        deSerializeDetails(productArray, "productsDetails");

        iiv.listTableModel = new DefaultTableModel(iiv.rowData, new String[] {
                "Product Id", "Product Name ", "Quantity" });

        productArray.forEach(product -> {
            iiv.listTableModel.addRow(
                    new Object[] { product.getProductId(), product.getProductName(),
                            product.getQuantityLeft() });
        });

        iiv.inventoryDetailsTable.setModel(iiv.listTableModel);

    }

    // mouse-event interface override
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == iiv.inventoryDetailsTable) {
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
}
