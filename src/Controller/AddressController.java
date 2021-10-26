package Controller;

import java.util.ArrayList;

import Model.Address;
import Model.City;

public class AddressController {

	/********************
     * Class Properties *
     ********************/

    public static ArrayList<Address> addresses = new ArrayList<Address>();

    /**********************
     * Class Constructors *
     **********************/

    public static Address create(final String street, final String number, final String neighborhood, final City city) {
    	Address newAddress = new Address(street, number, neighborhood, city);
    	
    	addresses.add(newAddress);
    	
    	return newAddress;
    }
    
    public static void delete(final Address address) {
    	addresses.remove(address);
    }
}
