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
    private JPanel form;
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
                if (deleteAction(selectedRow)) {
                    JOptionPane.showMessageDialog(form, "Record deleted successfuly!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(form, "Error deleting record!", "Error", JOptionPane.ERROR_MESSAGE);
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

        form = new JPanel();
        form.setLayout(null);
        form.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.add(form);

        btnSave = new JButton("Save");
        form.add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                case K_CREATE:

                    if (createAction()) {
                        JOptionPane.showMessageDialog(form, "Record created successfully.", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(form, "Please, fill all the gaps with '*'.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    break;

                case K_UPDATE:
                    if (updateAction(selectedRow)) {
                        JOptionPane.showMessageDialog(form, "Record updated successfully.", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(form, "Record does not exist.", "Error",
                                JOptionPane.ERROR_MESSAGE);
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
        form.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanForm();
                mode = K_DEFAULT;
                formMode(mode);
            }
        });
        btnCancel.setEnabled(false);

        form(form, btnCancel, btnSave);

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
            enableForm(true);

            break;

        case K_UPDATE:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true);

            break;

        case K_DELETE:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true);

            break;

        case K_SELECT:
            btnDelete.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnCreate.setEnabled(true);

            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            enableForm(false);

            break;

        default:
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnCreate.setEnabled(true);

            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            enableForm(false);

            break;
        }
    }

    /********************
     * Abstract Methods *
     ********************/

    protected abstract DefaultTableModel getTableModel();

    protected abstract void form(JPanel panel, JButton btnCancel, JButton btnSave);

    protected abstract void selectedRowAction(final int row);

    protected abstract boolean createAction();

    protected abstract boolean updateAction(final int row);

    protected abstract boolean deleteAction(final int row);

    protected abstract void enableForm(final boolean enabled);

    protected abstract void cleanForm();
}
