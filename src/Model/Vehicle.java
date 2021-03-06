package Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Vehicle implements IVehicle, Serializable {

	/********************
     * Class Properties *
     ********************/

	private static final long serialVersionUID = -1500250232663681235L;

    private final Model model;
    private final String chassis;
    private final int year;

    private String color;
    private String plate;
    private IClient owner;

    private Set<ServiceOrder> services;

    /**********************
     * Class Constructors *
     **********************/

    public Vehicle(final Model model, final String chassis, final int year, final String color) {
        this(model, chassis, year, color, null);
    }

    public Vehicle(final Model model, final String chassis, final int year, final String color, final String plate) {
        this.owner = null;
        this.model = model;
        this.chassis = chassis;
        this.year = year;
        this.color = color;
        this.plate = plate;
        this.services = new HashSet<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public String getChassis() {
        return chassis;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    public void setPlate(final String plate) {
        this.plate = plate;
    }

    @Override
    public IClient getOwner() {
        return owner;
    }

    public void setOwner(final IClient owner) {
        this.owner = owner;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    /**
     * Add a service to set of services
     * 
     * @param service
     */
    public void addService(ServiceOrder service) {
        services.add(service);
    }

    /**
     * Remove a service from services
     * 
     * @param service
     */
    public void removeService(ServiceOrder service) {
        services.remove(service);
    }

    /**
     * List all services made in the vehicle
     * 
     * @return StringBuilder
     */
    public StringBuilder listServices() {
        StringBuilder sb = new StringBuilder();

        sb.append("Servi??os feitos em " + this.getChassis() + "\n\n");

        for (ServiceOrder service : services) {
            sb.append(service.listServiceOrder());
        }

        return sb;
    }
}
