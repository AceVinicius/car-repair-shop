package Model;

import java.io.Serializable;

public class Address implements Serializable {

	/********************
     * Class Properties *
     ********************/
	
	private static final long serialVersionUID = -3470530584664032292L;

    private String street;
    private String number;
    private String neighborhood;
    private City city;

    /**********************
     * Class Constructors *
     **********************/

    public Address(final String street, final String number, final String neighborhood, final City city) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(final String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCity() {
        return city;
    }

    public void setCity(final City city) {
        this.city = city;
    }
}
