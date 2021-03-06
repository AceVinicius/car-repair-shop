package View;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.CityController;
import Controller.Controller;

public class CityView extends CrdPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 4214998848217485047L;

    private JTextField nameField;
    private JFormattedTextField stateField;

    private String name;
    private String state;

    /***********************
     * Getters and Setters *
     ***********************/

    private boolean getForm() {
        name = nameField.getText();
        state = stateField.getText();

        if (name.length() <= 0 || state.length() <= 0) {
            return false;
        }

        return true;
    }

    /********************
     * Abstract Methods *
     ********************/

    @Override
    protected DefaultTableModel getTableModel() {
        CityController cityController = Controller.getCityController();

        return cityController.getTableModel();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
        panel.setBounds(10, 289, 464, 90);

        JLabel nameLabel = new JLabel("Name*");
        nameLabel.setBounds(10, 26, 44, 14);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setEnabled(false);
        nameField.setColumns(10);
        nameField.setBounds(53, 23, 244, 20);
        panel.add(nameField);

        JLabel stateLabel = new JLabel("State*");
        stateLabel.setBounds(307, 26, 36, 14);
        panel.add(stateLabel);

        try {
            stateField = new JFormattedTextField(new MaskFormatter("UU"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        stateField.setBounds(353, 23, 101, 20);
        panel.add(stateField);
        stateField.setEnabled(false);
        stateField.setColumns(10);

        btnCancel.setBounds(129, 56, 89, 23);
        btnSave.setBounds(228, 56, 89, 23);
    }

    @Override
    protected void selectedRowAction(final Object id) {
        CityController cityController = Controller.getCityController();

        Object[] newRow = cityController.read(id);

        nameField.setText(newRow[0].toString());
        stateField.setText(newRow[1].toString());
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        CityController cityController = Controller.getCityController();

        cityController.create(name, state);
    }

    @Override
    protected void deleteAction(final Object id) throws CrudException {
        CityController cityController = Controller.getCityController();

        cityController.delete(id);
    }

    @Override
    protected void enableForm(final boolean enabled) {
        nameField.setEnabled(enabled);
        stateField.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        nameField.setText("");
        stateField.setText("");
    }
}
