package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class View {

    /********************
     * Class Properties *
     ********************/

    private JFrame frame;

    /**********************
     * Class Constructors *
     **********************/

    public View() {
        frame = new JFrame("Car Repair Shop");
        frame.setResizable(false);
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Create JMenuBar
         */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnNew = new JMenu("New");
        menuBar.add(mnNew);

        JMenuItem mntmNewCategory = new JMenuItem("Category");
        mntmNewCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Category Editor");
                frame.setBounds(100, 100, 500, 450);
                switchPanes(new CategoryView());
            }
        });
        mnNew.add(mntmNewCategory);

        JMenuItem mntmNewClient = new JMenuItem("Client");
        mntmNewClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Client Editor");
                frame.setBounds(100, 100, 500, 655);
                switchPanes(new ClientView());
            }
        });
        mnNew.add(mntmNewClient);

        JMenuItem mntmNewCity = new JMenuItem("City");
        mntmNewCity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("City Editor");
                frame.setBounds(100, 100, 500, 450);
                switchPanes(new CityView());
            }
        });
        mnNew.add(mntmNewCity);

        JMenuItem mntmNewEmployee = new JMenuItem("Employee");
        mntmNewEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Employee Editor");
                frame.setBounds(100, 100, 500, 626);
                switchPanes(new EmployeeView());
            }
        });
        mnNew.add(mntmNewEmployee);

        JMenuItem mntmNewItem = new JMenuItem("Item");
        mntmNewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Item Editor");
                frame.setBounds(100, 100, 500, 558);
                switchPanes(new ItemView());
            }
        });
        mnNew.add(mntmNewItem);

        JMenuItem mntmNewModel = new JMenuItem("Model");
        mntmNewModel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Model Editor");
                frame.setBounds(100, 100, 500, 450);
                switchPanes(new ModelView());
            }
        });
        mnNew.add(mntmNewModel);

        JMenuItem mntmNewService = new JMenuItem("Service");
        mntmNewService.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Service Editor");
                frame.setBounds(100, 100, 500, 660);
                switchPanes(new ServiceOrderView());
            }
        });
        mnNew.add(mntmNewService);

        JMenuItem mntmNewVehicle = new JMenuItem("Vehicle");
        mntmNewVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Vehicle Editor");
                frame.setBounds(100, 100, 500, 517);
                switchPanes(new VehicleView());
            }
        });
        mnNew.add(mntmNewVehicle);

        JSeparator separator = new JSeparator();
        mnNew.add(separator);

        JMenuItem mntnExit = new JMenuItem("Exit");
        mntnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnNew.add(mntnExit);

        frame.setVisible(true);
    }

    /******************************
     * Additional Private Methods *
     ******************************/

    private void switchPanes(JPanel newPanel) {
        JPanel panel = (JPanel) frame.getContentPane();

        panel.removeAll();
        panel.add(newPanel);
        panel.revalidate();
        panel.repaint();
    }
}