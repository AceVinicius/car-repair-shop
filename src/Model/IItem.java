package Model;

public interface IItem {
    public EItemType getType();

    public long getBarCode();

    public String getDescription();

    public double getPrice();
}
