package Model;

public class Address {

    /********************
     * Class Properties *
     ********************/

    private String street;
    private int number;
    private String neighborhood;
    private City city;

    /**********************
     * Class Constructors *
     **********************/

    public Address(final String street, final int number, final String neighborhood, final City city) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public String getNeighborheed() {
        return neighborhood;
    }

    public void setNeighborhood(final String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCitygetCity() {
        return city;
    }

    public void setCity(final City city) {
        this.city = city;
    }
}
