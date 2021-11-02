package Controller;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.IClient;
import Model.IVehicle;
import Model.Model;
import Model.Vehicle;
import View.CrudException;

public class VehicleController extends CrudController {

	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = -7966540195074986817L;

    public static Map<String, IVehicle> vehicles;

    /**********************
     * Class Constructors *
     **********************/

    public VehicleController() {
        vehicles = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<IVehicle> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IVehicle> model = new DefaultComboBoxModel<>();

        for (IVehicle vehicle : vehicles.values()) {
            model.addElement(vehicle);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "Chassis", "Year", "Color", "Plate", "Model", "Owner" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : vehicles.entrySet()) {
            Object[] row = { entry.getValue().getChassis(), entry.getValue().getYear(), entry.getValue().getColor(),
                    entry.getValue().getPlate(), entry.getValue().getModel(), entry.getValue().getOwner() };

            model.addRow(row);
        }

        return model;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final String chassis, final int year, final String color, final String plate, final Model model,
            final IClient client) throws CrudException {
        if (vehicles.get(chassis) != null) {
            throw new CrudException("Vehicle already exists.");
        }

        Vehicle newVehicle = new Vehicle(model, chassis, year, color);

        if (client != null) {
            newVehicle.setOwner(client);
        }
        if (plate != null) {
            newVehicle.setPlate(plate);
        }

        if (vehicles.put(chassis, newVehicle) != null) {
            throw new CrudException("Vehicle cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        IVehicle vehicle = vehicles.get((String) id);

        Object[] row = { vehicle.getChassis(), vehicle.getYear(), vehicle.getColor(),
                vehicle.getPlate() != null ? vehicle.getPlate() : "", vehicle.getModel(), vehicle.getOwner() };

        return row;
    }

    public void update(final Object id, final String color, final String plate, final IClient client)
            throws CrudException {
        Vehicle vehicle = (Vehicle) vehicles.get((String) id);

        if (vehicle == null) {
            throw new CrudException("Vehicle do not exists.");
        }

        vehicle.setColor(color);
        vehicle.setPlate(plate);
        vehicle.setOwner(client);

        Controller.writeFile();
    }

    public void delete(final Object id) throws CrudException {
        IVehicle vehicle = vehicles.get((String) id);

        if (vehicle == null) {
            throw new CrudException("Vehicle do not exists.");
        }

        vehicles.remove((String) id);

        Controller.writeFile();
    }
}
