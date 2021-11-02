package Model;

import java.io.Serializable;

public class City implements Serializable {

	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = -2149617449230564551L;

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
