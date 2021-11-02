package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Model;
import View.CrudException;

public class ModelController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    static Map<String, Model> models;

    /**********************
     * Class Constructors *
     **********************/

    public ModelController() {
        models = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<Model> getDefaultComboBoxModel() {
        DefaultComboBoxModel<Model> model = new DefaultComboBoxModel<>();

        for (Model m : models.values()) {
            model.addElement(m);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "Name" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : models.entrySet()) {
            Object[] row = { entry.getValue().getName() };

            model.addRow(row);
        }

        return model;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final String name) throws CrudException {
        if (models.get(name) != null) {
            throw new CrudException("Model already exists.");
        }

        Model newModel = new Model(name);

        if (models.put(name, newModel) != null) {
            throw new CrudException("Model cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        Model model = models.get((String) id);

        Object[] row = { model.getName() };

        return row;
    }

    public void delete(final Object id) throws CrudException {
        Model model = (Model) models.get((String) id);

        if (model == null) {
            throw new CrudException("Model do not exists.");
        }

        models.remove((String) id);

        Controller.writeFile();
    }
}
