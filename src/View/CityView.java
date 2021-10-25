package View;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.CityController;

public class CityView extends CrdView {

    /********************
     * Class Properties *
     ********************/

    private JTextField nameField;
    private JFormattedTextField stateField;
    private String name;
    private String state;

    /**********************
     * Class Constructors *
     **********************/

    public CityView(final String title, DefaultTableModel model) {
        super(title);
    }

    /***********************
     * Getters and Setters *
     ***********************/

    private void getForm() {
        name = nameField.getText();
        state = stateField.getText();
    }

    /********************
     * Abstract Methods *
     ********************/

    @Override
    protected DefaultTableModel getTableModel() {
        return CityController.getTableModel();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
        panel.setBounds(10, 289, 464, 90);

        nameField = new JTextField();
        nameField.setEnabled(false);
        nameField.setColumns(10);
        nameField.setBounds(74, 23, 233, 20);
        panel.add(nameField);

        try {
            stateField = new JFormattedTextField(new MaskFormatter("UU"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        stateField.setBounds(353, 23, 101, 20);
        panel.add(stateField);
        stateField.setEnabled(false);
        stateField.setColumns(10);

        JLabel nameLabel = new JLabel("City Name");
        nameLabel.setBounds(10, 26, 54, 14);
        panel.add(nameLabel);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(317, 26, 26, 14);
        panel.add(stateLabel);

        btnCancel.setBounds(129, 56, 89, 23);
        btnSave.setBounds(228, 56, 89, 23);
    }

    @Override
    protected void selectedRowAction(final int row) {
        Object[] newRow = CityController.retrieve(row);

        nameField.setText(newRow[0].toString());
        stateField.setText(newRow[1].toString());
    }

    @Override
    protected boolean createAction() {
        getForm();

        if (name.equals("") && state.equals("")) {
            return false;
        }

        if (!CityController.create(name, state)) {
            return false;
        }

        return true;
    }

    @Override
    protected void deleteAction(final int row) {
        if (CityController.delete(row)) {
            showMessageDialog(JOptionPane.INFORMATION_MESSAGE, "City deleted successfully!");
        } else {
            showMessageDialog(JOptionPane.ERROR_MESSAGE, "Error while deleting city!");
        }
    }

    @Override
    protected void enableForm(final boolean enabled) {
        nameField.setEnabled(enabled);
        stateField.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        nameField.setText(null);
        stateField.setText(null);
    }
}
