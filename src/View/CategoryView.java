package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Controller.CategoryController;

public class CategoryView extends CrdPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 4214998848217485047L;

    private JTextField nameField;
    private String name;

    /***********************
     * Getters and Setters *
     ***********************/

    private boolean getForm() {
        name = nameField.getText();

        if (name.length() <= 0) {
            return false;
        }

        return true;
    }

    /********************
     * Abstract Methods *
     ********************/

    @Override
    protected DefaultTableModel getTableModel() {
        CategoryController categoryController = Controller.getCategoryController();

        return categoryController.getTableCategory();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
        panel.setBounds(10, 289, 464, 90);

        JLabel nameLabel = new JLabel("Category*");
        nameLabel.setBounds(10, 26, 43, 14);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setEnabled(false);
        nameField.setColumns(10);
        nameField.setBounds(63, 23, 391, 20);
        panel.add(nameField);

        btnCancel.setBounds(129, 56, 89, 23);
        btnSave.setBounds(228, 56, 89, 23);
    }

    @Override
    protected void selectedRowAction(final Object id) {
        CategoryController categoryController = Controller.getCategoryController();

        Object[] newRow = categoryController.read(id);

        nameField.setText(newRow[0].toString());
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        CategoryController categoryController = Controller.getCategoryController();

        categoryController.create(name);
    }

    @Override
    protected void deleteAction(final Object id) throws CrudException {
        CategoryController categoryController = Controller.getCategoryController();

        categoryController.delete(id);
    }

    @Override
    protected void enableForm(boolean enabled) {
        nameField.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        nameField.setText("");
    }
}
