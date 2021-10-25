package View;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class View {

    /********************
     * Class Properties *
     ********************/

    private JFrame frame;

    /**********************
     * Class Constructors *
     **********************/

    public View(final String title) {
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.setBounds(100, 100, 500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        /*
         * Create JMenuBar
         */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnNew = new JMenu("New");
        menuBar.add(mnNew);

        JMenuItem mntmNewCity = new JMenuItem("City");
        mnNew.add(mntmNewCity);

        JSeparator separator = new JSeparator();
        mnNew.add(separator);

        JMenuItem mntnExit = new JMenuItem("Exit");
        mnNew.add(mntnExit);
    }

    /***********************
     * Getters and Setters *
     ***********************/
    protected Container getContentPane() {
        return frame.getContentPane();
    }

    protected void setVisible(final boolean visible) {
        frame.setVisible(visible);
        // frame.pack();
    }

    /********************************
     * Additional Protected Methods *
     ********************************/
    protected void showMessageDialog(final int type, final String message) {
        switch (type) {
        case JOptionPane.INFORMATION_MESSAGE:
            JOptionPane.showMessageDialog(frame, message, "Information", JOptionPane.INFORMATION_MESSAGE);
            break;

        case JOptionPane.WARNING_MESSAGE:
            JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
            break;

        case JOptionPane.QUESTION_MESSAGE:
            JOptionPane.showMessageDialog(frame, message, "Question", JOptionPane.QUESTION_MESSAGE);
            break;

        case JOptionPane.ERROR_MESSAGE:
        default:
            JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
            break;
        }
    }
}