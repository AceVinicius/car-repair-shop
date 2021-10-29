package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Address;
import Model.City;
import Model.Client;
import Model.IClient;

public class ClientController {

    /********************
     * Class Properties *
     ********************/

    public static ArrayList<IClient> clients = new ArrayList<IClient>();

    /**********************
     * Class Constructors *
     **********************/

    public static boolean create(final String cpf, final String name, final String telephone, final String email,
            final boolean platinum, final String street, final String number, final String neighborhood,
            final City city) {
        Address clientAddress = AddressController.create(street, number, neighborhood, city);

        Client newClient = new Client(cpf, name, telephone, clientAddress);
        newClient.setEmail(email);
        newClient.setPlatinum(platinum);

        return clients.add(newClient);
    }

    public static Object[] read(final int index) {
        IClient client = clients.get(index);

        Object[] row = { client.getCpf(), client.getName(), client.getTelephone(),
                client.getEmail() != null ? client.getEmail() : "", client.isPlatinum(),
                client.getAddress().getStreet(), client.getAddress().getNumber(), client.getAddress().getNeighborhood(),
                client.getAddress().getCity() };

        return row;
    }

    public static ArrayList<IClient> getAll() {
        return clients;
    }

    public static boolean update(final int index, final String name, final String telephone, final String email,
            final boolean platinum, final String street, final String number, final String neighborhood,
            final City city) {
        Client client = (Client) clients.get(index);

        if (client == null) {
            return false;
        }

        client.setName(name);
        client.setTelephone(telephone);
        client.setEmail(email);
        client.setPlatinum(platinum);
        client.getAddress().setStreet(street);
        client.getAddress().setNumber(number);
        client.getAddress().setNeighborhood(neighborhood);
        client.getAddress().setCity(city);

        return true;
    }

    public static boolean delete(final int index) {
        IClient client = clients.get(index);

        if (client == null) {
            return false;
        }

        AddressController.delete(client.getAddress());
        clients.remove(client);

        return true;
    }

    public static DefaultTableModel getTableModel() {
        Object[] header = { "CPF", "Name", "Telephone", "Email", "Platinum", "Street", "Number", "Neighborhood",
                "City" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (int i = 0; i < clients.size(); i++) {
            IClient curr = clients.get(i);
            model.addRow(new Object[] { curr.getCpf(), curr.getName(), curr.getTelephone(),
                    curr.getEmail() != null ? curr.getEmail() : "", curr.isPlatinum() == true ? "Yes" : "No",
                    curr.getAddress().getStreet(), curr.getAddress().getNumber(), curr.getAddress().getNeighborhood(),
                    curr.getAddress().getCity() });
        }

        return model;
    }
}
