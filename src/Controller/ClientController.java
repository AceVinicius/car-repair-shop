package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.Address;
import Model.City;
import Model.Client;
import Model.EmailException;
import Model.IClient;
import View.CrudException;

public class ClientController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    public Map<String, IClient> clients;

    /**********************
     * Class Constructors *
     **********************/

    public ClientController() {
        clients = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<IClient> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IClient> model = new DefaultComboBoxModel<>();

        for (IClient client : clients.values()) {
            model.addElement(client);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "CPF", "Name", "Telephone", "Email", "Platinum", "Street", "Number", "Neighborhood",
                "City" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : clients.entrySet()) {
            Object[] row = { entry.getValue().getCpf(), entry.getValue().getName(), entry.getValue().getTelephone(),
                    entry.getValue().getEmail(), entry.getValue().isPlatinum(),
                    entry.getValue().getAddress().getStreet(), entry.getValue().getAddress().getNumber(),
                    entry.getValue().getAddress().getNeighborhood(),
                    entry.getValue().getAddress().getCity().getName() };

            model.addRow(row);
        }

        return model;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final String cpf, final String name, final String telephone, final String email,
            final boolean platinum, final String street, final String number, final String neighborhood,
            final City city) throws CrudException, EmailException {
        if (clients.get(cpf) != null) {
            throw new CrudException("Client already exists.");
        }

        AddressController addressController = Controller.getAddressController();

        Address clientAddress = addressController.create(street, number, neighborhood, city);
        Client newClient = new Client(cpf, name, telephone, clientAddress);

        if (email != null) {
            newClient.setEmail(email);
        }

        if (platinum) {
            newClient.setPlatinum(true);
        }

        if (clients.put(cpf, newClient) != null) {
            throw new CrudException("Client cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        IClient client = clients.get((String) id);

        Object[] row = { client.getCpf(), client.getName(), client.getTelephone(),
                client.getEmail() != null ? client.getEmail() : "", client.isPlatinum(),
                client.getAddress().getStreet(), client.getAddress().getNumber(), client.getAddress().getNeighborhood(),
                client.getAddress().getCity() };

        return row;
    }

    public void update(final Object id, final String name, final String telephone, final String email,
            final boolean platinum, final String street, final String number, final String neighborhood,
            final City city) throws CrudException, EmailException {
        Client client = (Client) clients.get((String) id);

        if (client == null) {
            throw new CrudException("Client do not exists.");
        }

        client.setEmail(email);
        client.setName(name);
        client.setTelephone(telephone);
        client.setPlatinum(platinum);
        client.getAddress().setStreet(street);
        client.getAddress().setNumber(number);
        client.getAddress().setNeighborhood(neighborhood);
        client.getAddress().setCity(city);

        Controller.writeFile();
    }

    public void delete(final Object id) throws CrudException {
        IClient client = (IClient) clients.get((String) id);

        if (client == null) {
            throw new CrudException("Client do not exists.");
        }

        AddressController addressController = Controller.getAddressController();

        addressController.delete(client.getAddress());
        clients.remove((String) id);

        Controller.writeFile();
    }
}
