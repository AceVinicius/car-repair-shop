package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.City;

public class CityController {

    /********************
     * Class Properties *
     ********************/

    private static ArrayList<City> cities = new ArrayList<City>();

    /**********************
     * Class Constructors *
     **********************/

    public static boolean create(final String name, final String state) {
        City newCity = new City(name, state);
        cities.add(newCity);

        return true;
    }

    public static Object[] read(final int index) {
        City city = cities.get(index);

        Object[] row = { city.getName(), city.getState() };

        return row;
    }

    public static City getCity(final int index) {
        return cities.get(index);
    }

    public static ArrayList<City> getAll() {
        return cities;
    }

    public static boolean delete(final int index) {
        if (read(index) == null) {
            return false;
        }

        cities.remove(index);
        return true;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "Name", "State" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < cities.size(); i++) {
            model.addRow(new Object[] { cities.get(i).getName(), cities.get(i).getState() });
        }

        return model;
    }
}
