package Model;

public class ItemSO {

    /********************
     * Class Properties *
     ********************/

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
     * Get the price of the item multiplied by the quantity
     * of items.
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
    public String listItem() {
        return item.toString();
    }
}
