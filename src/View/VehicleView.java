package View;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.ClientController;
import Controller.ModelController;
import Controller.VehicleController;
import Model.IClient;
import Model.Model;

public class VehicleView extends CrudPanel {

	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = -7302848708659773408L;
    
    private JTextField chassisField;
    private JFormattedTextField yearField;
    private JTextField colorField;
    private JFormattedTextField plateField;
    private JComboBox<?> modelBox;
    private JComboBox<?> ownerBox;

    private String chassis;
    private String year;
    private String color;
    private String plate;
    private Model model;
    private IClient owner;

    /***********************
     * Getters and Setters *
     ***********************/

    private void getForm() throws InvalidFormException {
        chassis = chassisField.getText();
        year = yearField.getText();
        color = colorField.getText();
        plate = plateField.getText();
        model = (Model) modelBox.getSelectedItem();
        owner = (IClient) ownerBox.getSelectedItem();
        
        if (chassis.length() <= 0 || !isNumeric(year) || color.length() <= 0 || model == null) {
        	throw new InvalidFormException("Invalid Parameters.");
        }
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
        return VehicleController.getTableModel();
    }

    @Override
    protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
    	panel.setBounds(10, 289, 464, 158);
        
        JLabel chassisLabel = new JLabel("Chassis*");
        chassisLabel.setBounds(10, 24, 46, 14);
        panel.add(chassisLabel);
        
        chassisField = new JTextField();
        chassisField.setBounds(66, 21, 228, 20);
        panel.add(chassisField);
        chassisField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Year*");
        lblNewLabel.setBounds(304, 24, 35, 14);
        panel.add(lblNewLabel);
        
		try {
			yearField = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        yearField.setBounds(349, 21, 105, 20);
        panel.add(yearField);
        
        JLabel colorLabel = new JLabel("Color*");
        colorLabel.setBounds(10, 55, 46, 14);
        panel.add(colorLabel);

        colorField = new JTextField();
        colorField.setBounds(66, 52, 228, 20);
        panel.add(colorField);
        colorField.setColumns(10);

        JLabel plateLabel = new JLabel("Plate");
        plateLabel.setBounds(304, 55, 35, 14);
        panel.add(plateLabel);
        
		try {
			plateField = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        plateField.setBounds(349, 52, 105, 20);
        panel.add(plateField);
        
        JLabel modelLabel = new JLabel("Model*");
        modelLabel.setBounds(10, 87, 46, 14);
        panel.add(modelLabel);

        modelBox = new JComboBox<>( ModelController.getAll().toArray());
        modelBox.setBounds(66, 83, 160, 22);
        panel.add(modelBox);

        JLabel ownerLabel = new JLabel("Owner");
        ownerLabel.setBounds(248, 87, 46, 14);
        panel.add(ownerLabel);
        
        ownerBox = new JComboBox<>(ClientController.getAll().toArray());
        ownerBox.setBounds(294, 83, 160, 22);
        panel.add(ownerBox);

        btnCancel.setBounds(128, 124, 89, 23);
	    btnSave.setBounds(227, 124, 89, 23);
    }

    @Override
    protected void selectedRowAction(final int row) {
        Object[] newRow = VehicleController.read(row);

        chassisField.setText(newRow[0].toString());
        yearField.setText(newRow[1].toString());
        colorField.setText(newRow[2].toString());
        plateField.setText(newRow[3].toString());
        modelBox.setSelectedItem(newRow[4]);
        ownerBox.setSelectedItem(newRow[5]);
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
    	getForm();

        if (!VehicleController.create(chassis, Integer.parseInt(year), color, plate, model, owner)) {
        	throw new CrudException("Can't create new Vehicle. Something went wrong.");
        }
    }
    
    @Override
    protected void updateAction(final int row) throws InvalidFormException, CrudException {
    	getForm();

        if (!VehicleController.update(row, color, plate, owner)) {
        	throw new CrudException("Can't update selected Vehicle. Something went wrong.");
        }
    }

    @Override
    protected void deleteAction(final int row) throws CrudException {
        if (!VehicleController.delete(row)) {
        	throw new CrudException("Can't delete selected Vehicle. Something went wrong.");
        }
    }

    @Override
    protected void enableForm(final boolean enabled, final boolean isUpdate) {
		chassisField.setEnabled(isUpdate ? false : enabled);
		yearField.setEnabled(isUpdate ? false : enabled);
        colorField.setEnabled(enabled);
        plateField.setEnabled(enabled);
        ownerBox.setEnabled(enabled);
        modelBox.setEnabled(isUpdate ? false : enabled);
    }

    @Override
    protected void cleanForm() {
        chassisField.setText("");
        yearField.setText("");
        colorField.setText("");
        plateField.setText("");
    }
}
