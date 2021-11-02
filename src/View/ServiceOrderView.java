package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Controller.EmployeeController;
import Controller.ServiceOrderController;
import Controller.VehicleController;
import Model.Employee;
import Model.IEmployee;
import Model.IVehicle;
import Model.Vehicle;

public class ServiceOrderView extends CrudPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 1324718682557675857L;

    private JTextField mileageField;
    private JComboBox<IVehicle> vehicleBox;
    private JComboBox<IEmployee> consultantBox;
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
        ServiceOrderController serviceOrderController = Controller.getServiceOrderController();

        return serviceOrderController.getTableModel();
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

        VehicleController vehicleController = Controller.getVehicleController();

        vehicleBox = new JComboBox<IVehicle>(vehicleController.getDefaultComboBoxModel());
        vehicleBox.setBounds(76, 52, 152, 22);
        panel.add(vehicleBox);

        JLabel consultantLabel = new JLabel("Consultant");
        consultantLabel.setBounds(10, 23, 57, 14);
        panel.add(consultantLabel);

        EmployeeController employeeController = Controller.getEmployeeController();

        consultantBox = new JComboBox<IEmployee>(employeeController.getDefaultComboBoxModel());
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
    protected void selectedRowAction(final Object id) {
        ServiceOrderController serviceOrderController = Controller.getServiceOrderController();

        Object[] newRow = serviceOrderController.read(id);

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

        ServiceOrderController serviceOrderController = Controller.getServiceOrderController();

        serviceOrderController.create(Integer.parseInt(mileage), vehicle, consultant, description);
    }

    @Override
    protected void updateAction(final Object id) throws InvalidFormException, CrudException {
        if (!getForm()) {
            throw new InvalidFormException("Invalid Parameters.");
        }

        ServiceOrderController serviceOrderController = Controller.getServiceOrderController();

        serviceOrderController.update(id, Integer.parseInt(mileage), consultant, description);
    }

    @Override
    protected void deleteAction(final Object id) throws CrudException {
        ServiceOrderController serviceOrderController = Controller.getServiceOrderController();

        serviceOrderController.delete(id);
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
