package Controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Model.EItemType;
import Model.IItem;
import Model.Item;
import View.CrudException;

public class ItemController implements Serializable {

    /********************
     * Class Properties *
     ********************/

    public Map<Long, IItem> items;

    /**********************
     * Class Constructors *
     **********************/

    public ItemController() {
        items = new TreeMap<>();
    }

    /***********************
     * Getters and Setters *
     ***********************/

    public DefaultComboBoxModel<IItem> getDefaultComboBoxModel() {
        DefaultComboBoxModel<IItem> model = new DefaultComboBoxModel<>();

        for (IItem item : items.values()) {
            model.addElement(item);
        }

        return model;
    }

    public DefaultTableModel getTableModel() {
        Object[] header = { "Bar Code", "Price", "Description", "Type" };

        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (var entry : items.entrySet()) {
            Object[] row = { entry.getValue().getBarCode(), entry.getValue().getPrice(),
                    entry.getValue().getDescription(), entry.getValue().getType() };

            model.addRow(row);
        }

        return model;
    }

    /*****************************
     * Additional Public Methods *
     *****************************/

    public void create(final EItemType itemType, final String description, final double price) throws CrudException {
        Item newItem = new Item(itemType, description);

        newItem.setPrice(price);

        if (items.put(newItem.getBarCode(), newItem) != null) {
            throw new CrudException("City cannot be created.");
        }

        Controller.writeFile();
    }

    public Object[] read(final Object id) {
        IItem item = items.get((long) id);

        Object[] row = { item.getPrice(), item.getDescription(), item.getType() };

        return row;
    }

    public void update(final Object id, final double price) throws CrudException {
        Item item = (Item) items.get((long) id);

        if (item == null) {
            throw new CrudException("Item do not exists.");
        }

        item.setPrice(price);

        Controller.writeFile();
    }

    public void delete(final Object id) throws CrudException {
        IItem item = items.get((long) id);

        if (item == null) {
            throw new CrudException("Item do not exists.");
        }

        items.remove((long) id);

        Controller.writeFile();
    }
}
