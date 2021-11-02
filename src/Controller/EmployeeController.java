package Controller;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Address;
import Model.City;
import Model.Employee;
import Model.IEmployee;
import View.CrudException;

public class EmployeeController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    public Map<String, IEmployee> employees;

    /**********************
     * Class Constructors *
     **********************/

    public EmployeeController() {
        employees = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<IEmployee> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IEmployee> model = new DefaultComboBoxModel<>();

        for (IEmployee employee : employees.values()) {
            model.addElement(employee);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "CPF", "Name", "Telephone", "Email", "Street", "Number", "Neighborhood", "City" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : employees.entrySet()) {
            Object[] row = { entry.getValue().getCpf(), entry.getValue().getName(), entry.getValue().getTelephone(),
                    entry.getValue().getEmail(), entry.getValue().getAddress().getStreet(),
                    entry.getValue().getAddress().getNumber(), entry.getValue().getAddress().getNeighborhood(),
                    entry.getValue().getAddress().getCity() };

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

    public void create(final String cpf, final String name, final String telephone, final String email,
            final String street, final String number, final String neighborhood, final City city) throws CrudException {
        if (employees.get(cpf) != null) {
            throw new CrudException("Employee already exists.");
        }

        AddressController addressController = Controller.getAddressController();

        Address employeeAddress = addressController.create(street, number, neighborhood, city);
        Employee newEmployee = new Employee(cpf, name, telephone, employeeAddress);

        if (email != null) {
            newEmployee.setEmail(email);
        }

        if (employees.put(cpf, newEmployee) != null) {
            throw new CrudException("Employee cannot be created.");
        }

        persist();
    }

    public Object[] read(final Object id) {
        IEmployee employee = employees.get((String) id);

        Object[] row = { employee.getCpf(), employee.getName(), employee.getTelephone(),
                employee.getEmail() != null ? employee.getEmail() : "", employee.getAddress().getStreet(),
                employee.getAddress().getNumber(), employee.getAddress().getNeighborhood(),
                employee.getAddress().getCity() };

        return row;
    }

    public void update(final Object id, final String name, final String telephone, final String email,
            final String street, final String number, final String neighborhood, final City city) throws CrudException {
        Employee employee = (Employee) employees.get((String) id);

        if (employee == null) {
            throw new CrudException("Employee do not exists.");
        }

        employee.setName(name);
        employee.setTelephone(telephone);
        employee.setEmail(email);
        employee.getAddress().setStreet(street);
        employee.getAddress().setNumber(number);
        employee.getAddress().setNeighborhood(neighborhood);
        employee.getAddress().setCity(city);

        persist();
    }

    public void delete(final Object id) throws CrudException {
        IEmployee employee = (IEmployee) employees.get((String) id);

        if (employee == null) {
            throw new CrudException("Employee do not exists.");
        }

        AddressController addressController = Controller.getAddressController();

        addressController.delete(employee.getAddress());
        employees.remove((String) id);

        persist();
    }
}
