package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ItemController;
import Model.EItemType;

public class ItemView extends CrudPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 1324718682557675857L;

    private JComboBox<EItemType> itemTypeBox;
    private JTextArea descriptionArea;
    private JTextField priceField;

    private EItemType itemType;
    private String description;
    private String price;

    /***********************
     * Getters and Setters *
     ***********************/

    private boolean getForm() {
        itemType = (EItemType) itemTypeBox.getSelectedItem();
        description = descriptionArea.getText();
        price = priceField.getText();

        if (description.length() <= 0 || isNumeric(price) || itemType == null) {
            return false;
        }

        return true;
    }

    /******************************
     * Additional Private Methods *
     ******************************/

    private boolean isNumeric(final String str) {
        return str != null && str.matches("[0-9.]+");
    }

    /********************
     * Abstract Methods *
     ********************/

    @Override
    protected DefaultTableModel getTableModel() {
        return ItemController.getTableModel();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
        panel.setBounds(10, 289, 464, 199);

        JLabel priceLabel = new JLabel("Price*");
        priceLabel.setBounds(10, 23, 38, 14);
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(58, 20, 160, 20);
        panel.add(priceField);

        JLabel typeLabel = new JLabel("Type*");
        typeLabel.setBounds(228, 23, 38, 14);
        panel.add(typeLabel);

        itemTypeBox = new JComboBox<EItemType>(EItemType.values());
        itemTypeBox.setBounds(276, 19, 178, 22);
        panel.add(itemTypeBox);

        JLabel descriptionLabel = new JLabel("Description*");
        descriptionLabel.setBounds(10, 48, 65, 14);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(10, 73, 444, 76);
        panel.add(descriptionArea);

        btnCancel.setBounds(139, 160, 89, 23);
        btnSave.setBounds(238, 160, 89, 23);
    }

    @Override
    protected void selectedRowAction(final int row) {
        Object[] newRow = ItemController.read(row);

        priceField.setText(newRow[0].toString());
        itemTypeBox.setSelectedItem(newRow[1]);
        descriptionArea.setText(newRow[2].toString());
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        if (!ItemController.create(itemType, description, Double.parseDouble(price))) {
            throw new CrudException("Can't create new Item. Something went wrong.");
        }
    }

    @Override
    protected void updateAction(final int row) throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        if (!ItemController.update(row, Double.parseDouble(price))) {
            throw new CrudException("Can't update new Item. Something went wrong.");
        }
    }

    @Override
    protected void deleteAction(final int row) throws CrudException {
        if (!ItemController.delete(row)) {
            throw new CrudException("Can't delete new Item. Something went wrong.");
        }
    }

    @Override
    protected void enableForm(final boolean enabled, final boolean isUpdate) {
        itemTypeBox.setEnabled(isUpdate ? false : enabled);
        descriptionArea.setEnabled(isUpdate ? false : enabled);
        priceField.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        descriptionArea.setText("");
        priceField.setText("");
    }
}
