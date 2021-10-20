package Model;

import java.util.HashSet;

public class Vehicle implements IVehicle {

    /********************
     * Class Properties *
     ********************/

    private final Model model;
    private final String chassis;
    private final int year;

    private String color;
    private String plate;
    private IClient owner;

    private HashSet<ServiceOrder> services;

    /**********************
     * Class Constructors *
     **********************/

    public Vehicle(final IClient owner, final Model model, final String chassis, final int year,
            final String color) {
        this(owner, model, chassis, year, color, null);
    }

    public Vehicle(final IClient owner, final Model model, final String chassis, final int year,
            final String color, final String plate) {
        this.owner = owner;
        this.model = model;
        this.chassis = chassis;
        this.year = year;
        this.color = color;
        this.plate = plate;
        this.services = new HashSet<ServiceOrder>();
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

        sb.append("Serviços feitos em " + this.getChassis() + "\n\n");

        for (ServiceOrder service : services) {
            sb.append(service.listServiceOrder());
        }

        return sb;
    }
}
