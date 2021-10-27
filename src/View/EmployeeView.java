package View;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.CityController;
import Controller.EmployeeController;
import Model.City;

public class EmployeeView extends CrudPanel {

    /********************
     * Class Properties *
     ********************/
    private static final long serialVersionUID = -5904697069843558554L;

    private JFormattedTextField cpfField;
    private JTextField nameField;
    private JFormattedTextField telephoneField;
    private JTextField emailField;
    private JTextField streetField;
    private JFormattedTextField numberField;
    private JTextField neighborhoodField;
    private JComboBox<?> cityBox;

    private String cpf;
    private String name;
    private String telephone;
    private String email;
    private String street;
    private String number;
    private String neighborhood;
    private City city;

    /***********************
     * Getters and Setters *
     ***********************/

    private boolean getForm() {
        cpf = cpfField.getText();
        name = nameField.getText();
        telephone = telephoneField.getText();
        email = emailField.getText();
        street = streetField.getText();
        number = numberField.getText();
        neighborhood = neighborhoodField.getText();
        city = (City) cityBox.getSelectedItem();
        
        if (cpf.length() <= 0 || name.length() <= 0 || telephone.length() <= 0 || street.length() <= 0
                || number.length() <= 0 || cityBox.getSelectedItem() == null || neighborhood.length() <= 0) {
            return false;
        }
        
        return true;
    }

    /********************
     * Abstract Methods *
     ********************/

    @Override
    protected DefaultTableModel getTableModel() {
        return EmployeeController.getTableModel();
    }

    @Override
    protected void form(JPanel form, JButton btnCancel, JButton btnSave) {
        form.setBounds(10, 289, 464, 266);

        JLabel cpfLabel = new JLabel("CPF*");
        cpfLabel.setBounds(10, 26, 26, 14);
        form.add(cpfLabel);

        try {
            cpfField = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        cpfField.setEnabled(false);
        cpfField.setColumns(10);
        cpfField.setBounds(46, 23, 121, 20);
        form.add(cpfField);

        JLabel nameLabel = new JLabel("Name*");
        nameLabel.setBounds(177, 26, 34, 14);
        form.add(nameLabel);

        nameField = new JTextField();
        nameField.setEnabled(false);
        nameField.setColumns(10);
        nameField.setBounds(221, 23, 233, 20);
        form.add(nameField);

        JLabel telephoneLabel = new JLabel("Telephone*");
        telephoneLabel.setBounds(10, 57, 61, 14);
        form.add(telephoneLabel);

        try {
            telephoneField = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        telephoneField.setEnabled(false);
        telephoneField.setColumns(10);
        telephoneField.setBounds(75, 54, 121, 20);
        form.add(telephoneField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(206, 57, 46, 14);
        form.add(emailLabel);

        emailField = new JTextField();
        emailField.setEnabled(false);
        emailField.setColumns(10);
        emailField.setBounds(244, 54, 210, 20);
        form.add(emailField);

        JPanel address = new JPanel();
        address.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        address.setBounds(10, 90, 444, 129);
        form.add(address);
        address.setLayout(null);

        JLabel streetLabel = new JLabel("Street*");
        streetLabel.setBounds(10, 28, 36, 14);
        address.add(streetLabel);

        streetField = new JTextField();
        streetField.setBounds(56, 25, 378, 20);
        streetField.setEnabled(false);
        streetField.setColumns(10);
        address.add(streetField);

        JLabel numberLabel = new JLabel("Number*");
        numberLabel.setBounds(10, 59, 43, 14);
        address.add(numberLabel);

        try {
            numberField = new JFormattedTextField(new MaskFormatter("######"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numberField.setBounds(56, 56, 142, 20);
        numberField.setEnabled(false);
        numberField.setColumns(10);
        address.add(numberField);

        JLabel CityLabel = new JLabel("City*");
        CityLabel.setBounds(208, 59, 28, 14);
        address.add(CityLabel);

        cityBox = new JComboBox<>(CityController.getAll().toArray());
        cityBox.setEnabled(false);
        cityBox.setBounds(246, 54, 188, 22);
        address.add(cityBox);

        JLabel neighborhoodLabel = new JLabel("Neighborhood*");
        neighborhoodLabel.setBounds(10, 90, 424, 14);
        address.add(neighborhoodLabel);

        neighborhoodField = new JTextField();
        neighborhoodField.setBounds(93, 87, 341, 20);
        neighborhoodField.setEnabled(false);
        neighborhoodField.setColumns(10);
        address.add(neighborhoodField);

        btnCancel.setBounds(129, 232, 89, 23);
        btnSave.setBounds(228, 232, 89, 23);
    }

    @Override
    protected void selectedRowAction(final int row) {
        Object[] newRow = EmployeeController.read(row);

        cpfField.setText(newRow[0].toString());
        nameField.setText(newRow[1].toString());
        telephoneField.setText(newRow[2].toString());
        emailField.setText(newRow[3].toString());
        streetField.setText(newRow[4].toString());
        numberField.setText(newRow[5].toString());
        neighborhoodField.setText(newRow[6].toString());
        cityBox.setSelectedItem(newRow[7]);
    }

    @Override
    protected void createAction() throws InvalidFormException, CrudException {
    	if (!getForm()) {
    		throw new InvalidFormException("Invalid Parameters.");
    	}

        if (!EmployeeController.create(cpf, name, telephone, email, street, number, neighborhood, city)) {
        	throw new CrudException("Can't create new Employee. Something went wrong.");
        }
    }

    @Override
    protected void updateAction(final int row) throws InvalidFormException, CrudException {
    	if (!getForm()) {
    		throw new InvalidFormException("Invalid Parameters.");
    	}

        if (!EmployeeController.update(row, name, telephone, email, street, number, neighborhood, city)) {
        	throw new CrudException("Can't update new Employee. Something went wrong.");
        }
    }

    @Override
    protected void deleteAction(final int row) throws CrudException {
        if (!EmployeeController.delete(row)) {
        	throw new CrudException("Can't delete new Employee. Something went wrong.");
        }
    }

    @Override
    protected void enableForm(final boolean enabled, final boolean isUpdate) {
    	cpfField.setEnabled(isUpdate ? false : enabled);
        nameField.setEnabled(enabled);
        telephoneField.setEnabled(enabled);
        emailField.setEnabled(enabled);
        streetField.setEnabled(enabled);
        numberField.setEnabled(enabled);
        neighborhoodField.setEnabled(enabled);
        cityBox.setEnabled(enabled);
    }

    @Override
    protected void cleanForm() {
        cpfField.setText("");
        nameField.setText("");
        telephoneField.setText("");
        emailField.setText("");
        streetField.setText("");
        numberField.setText("");
        neighborhoodField.setText("");
    }
}
