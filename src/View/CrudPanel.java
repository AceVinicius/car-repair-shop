package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public abstract class CrudPanel extends JPanel {
    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = -161529371203750009L;

    private JTable table;
    private JPanel panel;
    private JButton btnUpdate;
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnCancel;
    private JButton btnSave;

    protected static final int K_DEFAULT = -1;
    protected static final int K_CREATE = 0;
    protected static final int K_UPDATE = 1;
    protected static final int K_DELETE = 2;
    protected static final int K_SELECT = 3;

    private DefaultTableModel model;
    private int selectedRow;
    private int mode;

    /**********************
     * Class Constructors *
     **********************/

    public CrudPanel() {
        /*
         * Create JTable
         */
        this.model = getTableModel();
        this.mode = K_DEFAULT;
        this.selectedRow = -1;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 13, 464, 233);
        this.add(scrollPane);
        table = new JTable(this.model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
                    selectedRowAction(selectedRow);
                    mode = K_SELECT;
                    formMode(mode);
                }
            }
        });
        scrollPane.setViewportView(table);

        /*
         * Create CRUD JButtons
         */
        btnCreate = new JButton("Create");
        btnCreate.setBounds(377, 255, 97, 23);
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanForm();
                mode = K_CREATE;
                formMode(mode);
            }
        });

        btnUpdate = new JButton("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(117, 255, 97, 23);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = K_UPDATE;
                formMode(mode);
            }
        });

        btnDelete = new JButton("Delete");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(10, 255, 97, 23);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		deleteAction(selectedRow);
            		JOptionPane.showMessageDialog(panel, "Record created successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);             		
            	} catch(CrudException ex) {
            		JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	}
                table.setModel(getTableModel());
                cleanForm();
                mode = K_DEFAULT;
                formMode(mode);
            }
        });

        this.setLayout(null);
        this.add(btnDelete);
        this.add(btnCreate);
        this.add(btnUpdate);

        /*
         * Create Form JPanel
         */

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(panel);

	    btnSave = new JButton("Save");
	    panel.add(btnSave);
	    btnSave.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            switch (mode) {
	            case K_CREATE:
	            	try {
	            		createAction();
	            		JOptionPane.showMessageDialog(panel, "Record created successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
	            	} catch(InvalidFormException ex) {
	            		JOptionPane.showMessageDialog(panel, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	            		return;
	            	} catch(CrudException ex) {
	            		JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	
	                break;
	
	            case K_UPDATE:
	            	try {
	            		updateAction(selectedRow);
	            		JOptionPane.showMessageDialog(panel, "Record Updated successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
	            	} catch(InvalidFormException ex) {
	            		JOptionPane.showMessageDialog(panel, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	            		return;
	            	} catch(CrudException ex) {
	            		JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	
	                break;
	            }
	            table.setModel(getTableModel());
	            cleanForm();
	            mode = K_DEFAULT;
	            formMode(mode);
	        }
	    });
	    btnSave.setEnabled(false);
                
        btnCancel = new JButton("Cancel");
        panel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanForm();
                mode = K_DEFAULT;
                formMode(mode);
            }
        });
        btnCancel.setEnabled(false);

        form(panel, btnCancel, btnSave);

        formMode(mode);
        setVisible(true);
    }

    /***********************
     * Getters and Setters *
     ***********************/

    protected void setModel(DefaultTableModel model) {
        this.model = model;
    }

    /******************************
     * Additional Private Methods *
     ******************************/

    private void formMode(final int mode) {
        switch (mode) {
        case K_CREATE:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true, false);

            break;

        case K_UPDATE:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true, true);

            break;

        case K_DELETE:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true, false);

            break;

        case K_SELECT:
            btnDelete.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnCreate.setEnabled(true);

            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            enableForm(false, false);

            break;

        default:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(true);

            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            enableForm(false, false);

            break;
        }
    }

    /********************
     * Abstract Methods *
     ********************/

    protected abstract DefaultTableModel getTableModel();

    protected abstract void form(JPanel panel, JButton btnCancel, JButton btnSave);

    protected abstract void selectedRowAction(final int row);

    protected abstract void createAction() throws InvalidFormException, CrudException;

    protected abstract void updateAction(final int row) throws InvalidFormException, CrudException;

    protected abstract void deleteAction(final int row) throws CrudException;

    protected abstract void enableForm(final boolean enabled, final boolean mode);

    protected abstract void cleanForm();
}
