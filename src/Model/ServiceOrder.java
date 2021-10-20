package Model;

import java.util.Date;
import java.util.HashSet;

public class ServiceOrder {

    /********************
     * Class Properties *
     ********************/

    private static int numberCounter = 1;
    private final int number;
    private final Date date;
    private final IClient client;
    private final IVehicle vehicle;

    private int kmAtual;
    private String description;
    private IEmployee consultant;

    private HashSet<ItemSO> items;

    /**********************
     * Class Constructors *
     **********************/

    public ServiceOrder(final IVehicle vehicle, final int kmAtual) {
        this.number = numberCounter++;
        this.vehicle = vehicle;
        this.kmAtual = kmAtual;
        this.date = new Date();
        this.client = vehicle.getOwner();
        this.items = new HashSet<ItemSO>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public int getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public IEmployee getConsultant() {
        return consultant;
    }

    public void setConsultant(final IEmployee consultant) {
        this.consultant = consultant;
    }

    public IVehicle getVehicle() {
        return vehicle;
    }

    public IClient getClient() {
        return client;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    /**
     * Add itemOS with stock price to items.
     * 
     * @param item
     * @param qtd
     */
    public void addItem(IItem item, int qtd) {
        addItem(item, qtd, item.getPrice());
    }

    /**
     * Add itemOS with custom price to items.
     * 
     * @param item
     * @param qtd
     * @param price
     */
    public void addItem(IItem item, int qtd, double price) {
        if (qtd > 0) {
            items.add(new ItemSO(item, qtd, price));
        }
    }

    /**
     * Remove itemOS from items.
     * 
     * @param item
     */
    public void removeItem(ItemSO item) {
        items.remove(item);
    }
    
    /**
     * Get total value from SERVICE only
     * 
     * @return
     */
    public double getServicesTotal() {
        double total = 0;

        for (ItemSO item : items) {
            if (item.getItem().getType() == EItemType.SERVICE) {
                total += item.getTotalItem();
            }
        }

        return total;
    }

    /**
     * Get total value from PART only
     * 
     * @return
     */
    public double getPartsTotal() {
        double total = 0;

        for (ItemSO item : items) {
            if (item.getItem().getType() == EItemType.PART) {
                total += item.getTotalItem();
            }
        }

        return total;
    }

    /**
     * Get total value from ServiceOrder
     * 
     * @return
     */
    public double getSOTotal() {
        double total = 0;

        for (ItemSO item : items) {
            total += item.getTotalItem();
        }

        return total;
    }

    

    public StringBuilder listServiceOrder() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("-----( Ordem de serviço " + this.getNumber() + " )-----\n\n");
        sb.append("Data:          " + this.getDate() + "\n");
        sb.append("KmAtual: " + this.getKmAtual() + "\n");
        sb.append("Descrição:     " + this.getDescription() + "\n");
        
        sb.append("\n-----( Consultor )-----\n");
        sb.append(this.getConsultant() + "\n");
        
        sb.append("\n-----( Cliente )-----\n");
        sb.append(this.getClient() + "\n");
        
        sb.append("\n-----( Veículo )-----\n");
        sb.append(this.getVehicle() + "\n");
        
        sb.append("\n-----( Itens )-----\n");
        sb.append(this.getItems());
        
        return sb;
    }

    /******************************
     * Additional Private Methods *
     ******************************/

    private StringBuilder getItems() {
        StringBuilder sb = new StringBuilder();
    
        double totalCarrinho = 0;
        double desconto = 0;
    
        for (ItemSO item : items) {
            int qtd = item.getQtd();
            double price = item.getPrice();
            double total = item.getTotalItem();
            IItem i = item.getItem();
    
            totalCarrinho += total;
    
            if (client.isPlatinum() && i.getType() == EItemType.SERVICE) {
                desconto += total;
            }
    
            sb.append("[" + i.getBarCode() + "] - [" + i.getDescription() + "] - [" + i.getType() + "]\n");
            sb.append(price + " x " + qtd + " = " + total + "\n");
            sb.append("-------------------\n");
        }
    
        sb.append("\nTotal:             " + totalCarrinho + "\n");
        sb.append("Desconto Platinum: " + desconto + "\n\n");
        sb.append("Total Carrinho:    " + (totalCarrinho - desconto));
    
        return sb;
    }
}
