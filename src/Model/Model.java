package Model;

import java.io.Serializable;

public class Model implements Serializable {

    /********************
     * Class Properties *
     ********************/

    private final String name;

    /**********************
     * Class Constructors *
     **********************/

    public Model(final String name) {
        this.name = name;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public String getName() {
        return name;
    }
}
