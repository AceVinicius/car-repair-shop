package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Address;
import Model.City;
import Model.Employee;
import Model.IEmployee;

public class EmployeeController {

    /********************
     * Class Properties *
     ********************/

    public static ArrayList<IEmployee> employees = new ArrayList<IEmployee>();

    /**********************
     * Class Constructors *
     **********************/

    public static boolean create(final String cpf, final String name, final String telephone, final String email,
            final String street, final String number, final String neighborhood, final City city) {
        Address employeeAddress = AddressController.create(street, number, neighborhood, city);

        Employee newEmployee = new Employee(cpf, name, telephone, employeeAddress);
        newEmployee.setEmail(email);

        return employees.add(newEmployee);
    }

    public static Object[] read(final int index) {
        IEmployee employee = employees.get(index);

        Object[] row = { employee.getCpf(), employee.getName(), employee.getTelephone(),
                employee.getEmail() != null ? employee.getEmail() : "", employee.getAddress().getStreet(),
                employee.getAddress().getNumber(), employee.getAddress().getNeighborhood(),
                employee.getAddress().getCity() };

        return row;
    }

    public static ArrayList<IEmployee> getAll() {
        return employees;
    }

    public static boolean update(final int index, final String name, final String telephone, final String email,
            final String street, final String number, final String neighborhood, final City city) {
        Employee employee = (Employee) employees.get(index);

        if (employee == null) {
            return false;
        }

        employee.setName(name);
        employee.setTelephone(telephone);
        employee.setEmail(email);
        employee.getAddress().setStreet(street);
        employee.getAddress().setNumber(number);
        employee.getAddress().setNeighborhood(neighborhood);
        employee.getAddress().setCity(city);

        return true;
    }

    public static boolean delete(final int index) {
        IEmployee employee = employees.get(index);

        if (employee == null) {
            return false;
        }

        AddressController.delete(employee.getAddress());
        employees.remove(employee);

        return true;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "CPF", "Name", "Telephone", "Email", "Street", "Number", "Neighborhood", "City" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < employees.size(); i++) {
            IEmployee curr = employees.get(i);
            model.addRow(new Object[] { curr.getCpf(), curr.getName(), curr.getTelephone(),
                    curr.getEmail() != null ? curr.getEmail() : "", curr.getAddress().getStreet(),
                    curr.getAddress().getNumber(), curr.getAddress().getNeighborhood(), curr.getAddress().getCity() });
        }

        return model;
    }
}
