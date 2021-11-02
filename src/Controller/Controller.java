package Controller;

import java.io.Serializable;

import Persistence.Persistence;

public class Controller implements Serializable {

    /********************
     * Class Properties *
     ********************/

    private static final long serialVersionUID = 1775856141150546052L;

    private static Controller instance;

    private AddressController addressController;
    private CityController cityController;
    private ClientController clientController;

    /**********************
     * Class Constructors *
     **********************/

    private Controller() {
        addressController = new AddressController();
        cityController = new CityController();
        clientController = new ClientController();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public static Controller getInstance() {
        return instance;
    }

    public static AddressController getAddressController() {
        return instance.addressController;
    }

    public static CityController getCityController() {
        return instance.cityController;
    }

    public static ClientController getClientController() {
        return instance.clientController;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public static void readFile() {
        instance = Persistence.readFile();

        if (instance == null) {
            instance = new Controller();
        }
    }

    public static void writeFile() {
        Persistence.writeFile(instance);
    }
}
