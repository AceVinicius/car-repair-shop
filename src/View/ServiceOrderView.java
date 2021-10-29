package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.EmployeeController;
import Controller.ServiceOrderController;
import Controller.VehicleController;
import Model.Employee;
import Model.Vehicle;

public class ServiceOrderView extends CrudPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 1324718682557675857L;

    private JTextField mileageField;
    private JComboBox<?> vehicleBox;
    private JComboBox<?> consultantBox;
    private JTextArea descriptionArea;

    private String mileage;
    private String description;
    private Vehicle vehicle;
    private Employee consultant;

    /***********************
     * Getters and Setters *
     ***********************/

    private boolean getForm() {
        mileage = mileageField.getText();
        vehicle = (Vehicle) vehicleBox.getSelectedItem();
        consultant = (Employee) consultantBox.getSelectedItem();
        description = descriptionArea.getText();

        if (!isNumeric(mileage) || vehicle == null) {
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
        return ServiceOrderController.getTableModel();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
        panel.setBounds(10, 289, 464, 300);

        JLabel mileageLabel = new JLabel("Mileage*");
        mileageLabel.setBounds(10, 90, 46, 14);
        panel.add(mileageLabel);

        mileageField = new JTextField();
        mileageField.setBounds(76, 87, 152, 20);
        panel.add(mileageField);
        mileageField.setColumns(10);

        JLabel vehicleLabel = new JLabel("Vehicle*");
        vehicleLabel.setBounds(10, 56, 46, 14);
        panel.add(vehicleLabel);

        vehicleBox = new JComboBox<>(VehicleController.getAll().toArray());
        vehicleBox.setBounds(76, 52, 152, 22);
        panel.add(vehicleBox);

        JLabel consultantLabel = new JLabel("Consultant");
        consultantLabel.setBounds(10, 23, 57, 14);
        panel.add(consultantLabel);

        consultantBox = new JComboBox<>(EmployeeController.getAll().toArray());
        consultantBox.setBounds(76, 19, 152, 22);
        panel.add(consultantBox);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(10, 123, 57, 14);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(76, 118, 378, 137);
        panel.add(descriptionArea);

        btnCancel.setBounds(139, 266, 89, 23);
        btnSave.setBounds(238, 266, 89, 23);
    }

    @Override
    protected void selectedRowAction(final int row) {
        Object[] newRow = ServiceOrderController.read(row);

        mileageField.setText(newRow[0].toString());
        consultantBox.setSelectedItem(newRow[1]);
        vehicleBox.setSelectedItem(newRow[2]);
        descriptionArea.setText(newRow[3].toString());
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        if (!ServiceOrderController.create(Integer.parseInt(mileage), vehicle, consultant, description)) {
            throw new CrudException("Can't create new Item. Something went wrong.");
        }
    }

    @Override
    protected void updateAction(final int row) throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        if (!ServiceOrderController.update(row, Integer.parseInt(mileage), consultant, description)) {
            throw new CrudException("Can't update new Item. Something went wrong.");
        }
    }

    @Override
    protected void deleteAction(final int row) throws CrudException {
        if (!ServiceOrderController.delete(row)) {
            throw new CrudException("Can't delete new Item. Something went wrong.");
        }
    }

    @Override
    protected void enableForm(final boolean enabled, final boolean isUpdate) {
        vehicleBox.setEnabled(isUpdate ? false : enabled);
        mileageField.setEnabled(enabled);
        descriptionArea.setEnabled(enabled);
        consultantBox.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        mileageField.setText("");
        descriptionArea.setText("");
    }
}
