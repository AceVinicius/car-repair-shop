package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.City;
import View.CrudException;

public class CityController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 4419346585471927334L;

    private Map<String, City> cities;

    /**********************
     * Class Constructors *
     **********************/

    public CityController() {
        cities = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public City getCity(final Object id) {
        return cities.get((String) id);
    }

    public DefaultComboBoxModel<City> getDefaultComboBoxModel() {
        DefaultComboBoxModel<City> model = new DefaultComboBoxModel<>();

        for (City city : cities.values()) {
            model.addElement(city);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "Name", "State" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : cities.entrySet()) {
            Object[] row = { entry.getValue().getName(), entry.getValue().getState() };

            model.addRow(row);
        }

        return model;
    }

    /******************************
     * Additional Private Methods *
     ******************************/

    private void persist() {
        Controller.writeFile();
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final String name, final String state) throws CrudException {
        if (cities.get(name) != null) {
            throw new CrudException("City already exists.");
        }

        City newCity = new City(name, state);

        if (cities.put(name, newCity) != null) {
            throw new CrudException("City cannot be created.");
        }

        persist();
    }

    public Object[] read(final Object id) {
        City city = cities.get((String) id);

        Object[] row = { city.getName(), city.getState() };

        return row;
    }

    public void delete(final Object id) {
        cities.remove((String) id);

        persist();
    }
}
