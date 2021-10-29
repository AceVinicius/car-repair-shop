package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.IClient;
import Model.IVehicle;
import Model.Model;
import Model.Vehicle;

public class VehicleController {

    /********************
     * Class Properties *
     ********************/

    public static ArrayList<IVehicle> vehicles = new ArrayList<IVehicle>();

    /**********************
     * Class Constructors *
     **********************/

    public static boolean create(final String chassis, final int year, final String color, final String plate,
            final Model model, final IClient client) {
        Vehicle newVehicle = new Vehicle(model, chassis, year, color);

        if (client != null) {
            newVehicle.setOwner(client);
        }

        if (plate.length() <= 0) {
            newVehicle.setPlate(plate);
        }

        return vehicles.add(newVehicle);
    }

    public static Object[] read(final int index) {
        IVehicle vehicle = vehicles.get(index);

        Object[] row = { vehicle.getChassis(), vehicle.getYear(), vehicle.getColor(),
                vehicle.getPlate() != null ? vehicle.getPlate() : "", vehicle.getModel(), vehicle.getOwner() };

        return row;
    }

    public static ArrayList<IVehicle> getAll() {
        return vehicles;
    }

    public static boolean update(final int index, final String color, final String plate, final IClient client) {
        Vehicle vehicle = (Vehicle) vehicles.get(index);

        if (vehicle == null) {
            return false;
        }

        vehicle.setColor(color);
        vehicle.setPlate(plate);
        vehicle.setOwner(client);

        return true;
    }

    public static boolean delete(final int index) {
        IVehicle vehicle = vehicles.get(index);

        if (vehicle == null) {
            return false;
        }

        vehicles.remove(vehicle);

        return true;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "Chassis", "Year", "Color", "Plate", "Model", "Owner" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < vehicles.size(); i++) {
            IVehicle curr = vehicles.get(i);
            model.addRow(new Object[] { curr.getChassis(), curr.getYear(), curr.getColor(),
                    curr.getPlate() != null ? curr.getPlate() : "", curr.getModel(), curr.getOwner() });
        }

        return model;
    }
}
