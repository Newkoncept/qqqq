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
    int num = 0;

    public SalesController() {
        deSerializeDetails(salesArray, "salesDetails");

        ssv.setVisible(true);

        populateAllView();

        // APPending Action Listeners
        ssv.salesBackButton.addActionListener(this);

    }

    /**
     *
     * Action Controller for the page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ssv.salesBackButton) {
            Navigator nv = new Navigator();
            ssv.setVisible(false);
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

        buttonEnabler(false);
    }

    public void buttonEnabler(boolean setEnabled) {
        ssv.newSalesButton.setEnabled(setEnabled);
    }

    // mouse-event interface override
    @Override
    public void mouseClicked(MouseEvent e) {
        // arrayLength = staffArray.size();

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

}
