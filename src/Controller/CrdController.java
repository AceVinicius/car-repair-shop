package Controller;

import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import View.CrudException;

public abstract class CrdController implements Serializable {

	private static final long serialVersionUID = -4431117054900822141L;

	public abstract DefaultComboBoxModel<?> getDefaultComboBoxModel();

    public abstract DefaultTableModel getTableModel();

    // public abstract void create() throws CrudException;

    public abstract Object[] read(final Object id);

    public abstract void delete(final Object id) throws CrudException;
}