package Model;

import java.io.Serializable;

public class ItemSO implements Serializable {

	/********************
     * Class Properties *
     ********************/
	
	private static final long serialVersionUID = 2366703755225373537L;

    private final IItem item;

    private int qtd;
    private double price;

    /**********************
     * Class Constructors *
     **********************/

    public ItemSO(final IItem item, final int qtd, final double price) {
        this.qtd = qtd;
        this.price = price;
        this.item = item;
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public IItem getItem() {
        return item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    /**
     * Get the price of the item multiplied by the quantity of items.
     * 
     * @return
     */
    public double getTotalItem() {
        return getPrice() * getQtd();
    }

    /**
     * Show information about this item.
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s - %d - %.2f", getItem().getDescription(), getQtd(), getTotalItem());
    }
}
