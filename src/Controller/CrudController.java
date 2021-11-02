package Controller;

import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import View.CrudException;

public abstract class CrudController implements Serializable {

	private static final long serialVersionUID = -7462722714479963906L;

	public abstract DefaultComboBoxModel<?> getDefaultComboBoxModel();

    public abstract DefaultTableModel getTableModel();

    // public abstract void create() throws CrudException;

    public abstract Object[] read(final Object id);

    // public abstract void update() throws CrudException, EmailException;

    public abstract void delete(final Object id) throws CrudException;
}