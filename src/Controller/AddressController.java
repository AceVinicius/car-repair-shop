package Controller;

import java.io.Serializable;
import java.util.ArrayList;

import Model.Address;
import Model.City;

public class AddressController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    public static ArrayList<Address> addresses = new ArrayList<Address>();

    /**********************
     * Class Constructors *
     **********************/

    public Address create(final String street, final String number, final String neighborhood, final City city) {
        Address newAddress = new Address(street, number, neighborhood, city);

        addresses.add(newAddress);

        Controller.writeFile();

        return newAddress;
    }

    public void delete(final Address address) {
        addresses.remove(address);

        Controller.writeFile();
    }
}
