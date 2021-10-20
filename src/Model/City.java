package Model;

public class City {

    /********************
     * Class Properties *
     ********************/

    private final String name;
    private final String state;

    /**********************
     * Class Constructors *
     **********************/

    public City(final String name, final String state) {
        this.name = name;
        this.state = state;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
