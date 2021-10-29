package Controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.IEmployee;
import Model.IVehicle;
import Model.ServiceOrder;

public class ServiceOrderController {

	/********************
	 * Class Properties *
	 ********************/

	public static ArrayList<ServiceOrder> services = new ArrayList<ServiceOrder>();

	/**********************
	 * Class Constructors *
	 **********************/

	public static boolean create(final int mileage, final IVehicle vehicle, final IEmployee employee, final String description) {
		ServiceOrder newServiceOrder = new ServiceOrder(vehicle, mileage);
		
		if (employee != null) {
			newServiceOrder.setConsultant(employee);
		}
		if (description.length() > 0) {
			newServiceOrder.setDescription(description);			
		}

		return services.add(newServiceOrder);
	}

	public static Object[] read(final int index) {
		ServiceOrder item = services.get(index);

		Object[] row = {
			item.getNumber(),
			item.getDate(),
			item.getClient(),
			item.getVehicle(),
			item.getMileage(),
			item.getConsultant()
		};

		return row;
	}

	public static boolean update(final int index, final int mileage, final IEmployee employee, final String description) {
		ServiceOrder item = (ServiceOrder) services.get(index);

		if (item == null) {
			return false;
		}

		item.setMileage(mileage);
		item.setConsultant(employee);
		item.setDescription(description);

		return true;
	}

	public static boolean delete(final int index) {
		ServiceOrder item = services.get(index);

		if (item == null) {
			return false;
		}

		return services.remove(item);
	}

	public static DefaultTableModel getTableModel() {
		Object[] header = { "Number", "Date", "Client", "Vehicle", "Mileage", "Consultant" };

		DefaultTableModel model = new DefaultTableModel(header, 0);

		for (int i = 0; i < services.size(); i++) {
			ServiceOrder curr = services.get(i);
			model.addRow(new Object[] { 
				curr.getNumber(),
				curr.getDate(),
				curr.getClient(),
				curr.getVehicle(),
				curr.getMileage(),
				curr.getConsultant()
			});
		}

		return model;
	}
}
