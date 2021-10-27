package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Model;

public class ModelController {

    /********************
     * Class Properties *
     ********************/

    private static ArrayList<Model> models = new ArrayList<Model>();

    /**********************
     * Class Constructors *
     **********************/

    public static boolean create(final String name) {
        Model newModel = new Model(name);
        models.add(newModel);

        return true;
    }

    public static Object[] read(final int index) {
        Model model = models.get(index);

        Object[] row = { model.getName() };

        return row;
    }

    public static Model getModel(final int index) {
        return models.get(index);
    }

    public static ArrayList<Model> getAll() {
        return models;
    }

    public static boolean delete(final int index) {
        if (read(index) == null) {
            return false;
        }

        models.remove(index);
        return true;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "Name" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < models.size(); i++) {
            model.addRow(new Object[] { models.get(i).getName() });
        }

        return model;
    }
}
