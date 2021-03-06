package Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Category implements Serializable {

	/********************
     * Class Properties *
     ********************/
	
	private static final long serialVersionUID = -707482728977846790L;

    private final String name;

    private Set<IItem> items;

    /**********************
     * Class Constructors *
     **********************/

    public Category(final String name) {
        this.name = name;
        this.items = new HashSet<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public String getName() {
        return name;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void addItem(final IItem item) {
        items.add(item);
    }

    public void removeItem(final IItem item) {
        items.remove(item);
    }

    public StringBuilder listItems() {
        StringBuilder sb = new StringBuilder();

        for (IItem item : items) {
            sb.append(" | |\n");
            sb.append(" | |-.-( Item " + item.getBarCode() + " )\n");
            sb.append(" | | |\n");
            sb.append(" | | |- Código:    " + item.getBarCode() + "\n");
            sb.append(" | | |- Tipo:      " + item.getType() + "\n");
            sb.append(" | | |- Descrição: " + item.getDescription() + "\n");
            sb.append(" | | `- Preço:     " + item.getPrice() + "\n");
        }

        return sb;
    }
}
