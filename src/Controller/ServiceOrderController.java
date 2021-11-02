package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.IEmployee;
import Model.IVehicle;
import Model.ServiceOrder;
import View.CrudException;

public class ServiceOrderController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    public static Map<Integer, ServiceOrder> services;

    /**********************
     * Class Constructors *
     **********************/

    public ServiceOrderController() {
        services = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<ServiceOrder> getDefaultComboBoxModel() {
        DefaultComboBoxModel<ServiceOrder> model = new DefaultComboBoxModel<>();

        for (ServiceOrder service : services.values()) {
            model.addElement(service);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "Number", "Date", "Client", "Vehicle", "Mileage", "Consultant" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : services.entrySet()) {
            Object[] row = { entry.getKey(), entry.getValue().getDate(), entry.getValue().getClient().getName(),
                    entry.getValue().getVehicle().getPlate(), entry.getValue().getMileage(),
                    entry.getValue().getConsultant().getName() };

            model.addRow(row);
        }

        return model;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final int mileage, final IVehicle vehicle, final IEmployee employee, final String description)
            throws CrudException {
        ServiceOrder newServiceOrder = new ServiceOrder(vehicle, mileage);

        if (employee != null) {
            newServiceOrder.setConsultant(employee);
        }
        if (description.length() > 0) {
            newServiceOrder.setDescription(description);
        }

        if (services.put(newServiceOrder.getNumber(), newServiceOrder) != null) {
            throw new CrudException("Employee cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        ServiceOrder service = services.get((Integer) id);

        Object[] row = { service.getNumber(), service.getDate(), service.getClient(), service.getVehicle(),
                service.getMileage(), service.getConsultant() };

        return row;
    }

    public void update(final Object id, final int mileage, final IEmployee employee, final String description)
            throws CrudException {
        ServiceOrder service = services.get((Integer) id);

        if (service == null) {
            throw new CrudException("Service Order do not exists.");
        }

        service.setMileage(mileage);
        service.setConsultant(employee);
        service.setDescription(description);

        Controller.writeFile();
    }

    public void delete(final Object id) throws CrudException {
        ServiceOrder service = services.get(id);

        if (service == null) {
            throw new CrudException("Service Order do not exists.");
        }

        services.remove((Integer) id);

        Controller.writeFile();
    }
}
