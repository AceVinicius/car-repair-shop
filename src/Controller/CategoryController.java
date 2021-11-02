package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Category;
import View.CrudException;

public class CategoryController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    static Map<String, Category> categories;

    /**********************
     * Class Constructors *
     **********************/

    public CategoryController() {
        categories = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<Category> getDefaultComboBoxCategory() {
        DefaultComboBoxModel<Category> model = new DefaultComboBoxModel<>();

        for (Category category : categories.values()) {
            model.addElement(category);
        }

        return model;
    }

    public DefaultTableModel getTableCategory() {
        Object[] header = { "Name" };

        DefaultTableModel category = new DefaultTableModel(header, 0);

        for (var entry : categories.entrySet()) {
            Object[] row = { entry.getValue().getName() };

            category.addRow(row);
        }

        return category;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final String name) throws CrudException {
        if (categories.get(name) != null) {
            throw new CrudException("Category already exists.");
        }

        Category newCategory = new Category(name);

        if (categories.put(name, newCategory) != null) {
            throw new CrudException("Category cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        Category category = categories.get((String) id);

        Object[] row = { category.getName() };

        return row;
    }

    public void delete(final Object id) throws CrudException {
        Category category = (Category) categories.get((String) id);

        if (category == null) {
            throw new CrudException("Category do not exists.");
        }

        categories.remove((String) id);

        Controller.writeFile();
    }
}
