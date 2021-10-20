package Model;

public interface IVehicle {
    public Model getModel();

    public String getChassis();

    public int getYear();

    public String getColor();

    public String getPlate();

    public IClient getOwner();
}
