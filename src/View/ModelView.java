package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ModelController;

public class ModelView extends CrdPanel {

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
		return ModelController.getTableModel();
	}

	@Override
	protected void form(JPanel panel, JButton btnCancel, JButton btnSave) {
		panel.setBounds(10, 289, 464, 90);
		
		JLabel nameLabel = new JLabel("Model*");
        nameLabel.setBounds(10, 26, 54, 14);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setEnabled(false);
        nameField.setColumns(10);
        nameField.setBounds(74, 23, 233, 20);
        panel.add(nameField);
        
        btnCancel.setBounds(129, 56, 89, 23);
        btnSave.setBounds(228, 56, 89, 23);
	}

	@Override
	protected void selectedRowAction(final int row) {
        Object[] newRow = ModelController.read(row);

        nameField.setText(newRow[0].toString());
	}

	@Override
	protected void createAction() throws InvalidFormException, CrudException {
		if (!getForm()) {
			throw new InvalidFormException("Invalid Parameters.");
    	}

        if (!ModelController.create(name)) {
        	throw new CrudException("Can't create selected Vehicle. Something went wrong.");
        }
	}

	@Override
	protected void deleteAction(final int row) throws CrudException {
		if (!ModelController.delete(row)) {
			throw new CrudException("Can't delete selected Vehicle. Something went wrong.");
        }
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
