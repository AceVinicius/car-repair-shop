package Model;

import java.io.Serializable;

public class Item implements IItem, Serializable {

    /********************
     * Class Properties *
     ********************/

    private static int barCodeCounter = 1;

    private final int barCode;
    private final EItemType type;
    private final String description;

    private double price;

    /**********************
     * Class Constructors *
     **********************/

    public Item(final EItemType type, final String description) {
        this.type = type;
        this.barCode = barCodeCounter++;
        this.description = description;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    @Override
    public long getBarCode() {
        return barCode;
    }

    @Override
    public EItemType getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    /**
     * Show information about this item.
     * 
     * @return
     */
    public StringBuilder listItem() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código:    " + this.getBarCode() + "\n");
        sb.append("Tipo:      " + this.getType() + "\n");
        sb.append("Descrição: " + this.getDescription() + "\n");
        sb.append("Preço:     " + this.getPrice());

        return sb;
    }
}
