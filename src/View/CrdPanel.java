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

public abstract class CrdPanel extends JPanel {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 4828543939584285181L;

    private JTable table;
    private JPanel panel;
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
    private Object id;
    private int mode;

    /**********************
     * Class Constructors *
     **********************/

    public CrdPanel() {
        /*
         * Create JTable
         */
        this.model = getTableModel();
        this.mode = K_DEFAULT;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 13, 464, 233);
        this.add(scrollPane);
        table = new JTable(this.model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
                    id = table.getValueAt(selectedRow, 0);
                    selectedRowAction(id);
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

        btnDelete = new JButton("Delete");
        btnDelete.setEnabled(false);
        btnDelete.setBounds(10, 255, 97, 23);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteAction(id);
                    table.setModel(getTableModel());
                    cleanForm();
                    mode = K_DEFAULT;
                    formMode(mode);
                    JOptionPane.showMessageDialog(panel, "Record created successfully.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (CrudException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setLayout(null);
        this.add(btnDelete);
        this.add(btnCreate);

        /*
         * Create Form JPanel
         */
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanForm();
                mode = K_DEFAULT;
                formMode(mode);
            }
        });
        btnCancel.setEnabled(false);
        panel.add(btnCancel);

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    createAction();
                    table.setModel(getTableModel());
                    cleanForm();
                    mode = K_DEFAULT;
                    formMode(mode);
                    JOptionPane.showMessageDialog(panel, "Record created successfully.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (InvalidFormException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                } catch (CrudException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSave.setEnabled(false);
        panel.add(btnSave);

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
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true);

            break;

        case K_DELETE:
            btnDelete.setEnabled(false);
            btnCreate.setEnabled(false);

            btnCancel.setEnabled(true);
            btnSave.setEnabled(true);
            enableForm(true);

            break;

        case K_SELECT:
            btnDelete.setEnabled(true);
            btnCreate.setEnabled(true);

            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            enableForm(false);

            break;

        default:
            btnDelete.setEnabled(false);
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

    protected abstract void selectedRowAction(final Object id);

    protected abstract void createAction() throws InvalidFormException, CrudException;

    protected abstract void deleteAction(final Object id) throws CrudException;

    protected abstract void enableForm(final boolean enabled);

    protected abstract void cleanForm();
}
