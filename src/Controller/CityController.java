package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.City;
import View.CityView;

public class CityController {

    /********************
     * Class Properties *
     ********************/

    public static ArrayList<City> cities = new ArrayList<City>();

    /**********************
     * Class Constructors *
     **********************/

    public CityController() {
        new CityView("City Editor", getTableModel());
    }

    public static boolean create(final String name, final String state) {
        City newCity = new City(name, state);
        cities.add(newCity);

        return true;
    }

    public static Object[] retrieve(final int index) {
        City city = cities.get(index);

        Object[] row = { city.getName(), city.getState() };

        return row;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "Name", "State" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < cities.size(); i++) {
            model.addRow(new Object[] { cities.get(i).getName(), cities.get(i).getState() });
        }

        return model;
    }

    public static boolean delete(final int index) {
        if (retrieve(index) == null) {
            return false;
        }

        cities.remove(index);
        return true;
    }
}
