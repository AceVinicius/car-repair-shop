package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.EItemType;
import Model.IItem;
import Model.Item;

public class ItemController {

	/********************
	 * Class Properties *
	 ********************/

	public static ArrayList<IItem> items = new ArrayList<IItem>();

	/**********************
	 * Class Constructors *
	 **********************/

	public static boolean create(final EItemType itemType, final String description, final double price) {
		Item newItem = new Item(itemType, description);
		newItem.setPrice(price);

		return items.add(newItem);
	}

	public static Object[] read(final int index) {
		IItem item = items.get(index);

		Object[] row = {
			item.getPrice(), 
			item.getDescription(), 
			item.getType()
		};

		return row;
	}

	public static boolean update(final int index, final double price) {
		Item item = (Item) items.get(index);

		if (item == null) {
			return false;
		}

		item.setPrice(price);

		return true;
	}

	public static boolean delete(final int index) {
		IItem item = items.get(index);

		if (item == null) {
			return false;
		}

		return items.remove(item);
	}

	public static DefaultTableModel getTableModel() {
		Object[] header = { "Bar Code", "Price", "Description", "Type" };

		DefaultTableModel model = new DefaultTableModel(header, 0);

		for (int i = 0; i < items.size(); i++) {
			IItem curr = items.get(i);
			model.addRow(new Object[] { 
				curr.getBarCode(),
				curr.getPrice(),
				curr.getDescription(),
				curr.getType()
			});
		}

		return model;
	}
}
