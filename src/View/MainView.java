package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainView implements ActionListener {
    private JFrame window;
    
    private JLabel a = new JLabel();
    private JLabel b = new JLabel();
    
    private JPanel top;
    private JPanel bottom;
    private JPanel left;
    private JPanel right;
    private JPanel content;

    private JButton d = new JButton();


    private JMenuBar menuBar;

    private JMenu menu1;
    private JMenuItem menu1item1;
    private JMenuItem menu1item2;
    private JMenuItem exitMenuItem;
    
    private JMenu menu2;
    private JMenuItem menu2item1;
    private JMenuItem menu2item2;

    private JMenu menu3;
    private JMenuItem menu3item1;

    private JMenu helpMenu;
    private JMenuItem aboutMe;


    public MainView (final String title) {
        this.window = new JFrame(title);

        window.setSize(500, 500);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        menuBar = new JMenuBar();
        
        menu1 = new JMenu("Menu 1");
        menu2 = new JMenu("Menu 2");
        menu3 = new JMenu("Menu 3");
        helpMenu = new JMenu("Help");
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(helpMenu);

        
        menu1item1 = new JMenuItem("Item 1");
        menu1item2 = new JMenuItem("Item 2");
        exitMenuItem = new JMenuItem("Exit");
        
        menu1item1.addActionListener(this);
        menu1item2.addActionListener(this);
        exitMenuItem.addActionListener(this);
        
        menu1.add(menu1item1);
        menu1.add(menu1item2);
        menu1.add(exitMenuItem);
        
        
        menu2item1 = new JMenuItem("Item 1");
        menu2item2 = new JMenuItem("Item 2");
        
        menu2item1.addActionListener(this);
        menu2item2.addActionListener(this);
        
        menu2.add(menu2item1);
        menu2.add(menu2item2);
        
        
        menu3item1 = new JMenuItem("Item 1");
        
        menu3item1.addActionListener(this);
        
        menu3.add(menu3item1);
        
        
        aboutMe = new JMenuItem("About Me");
        
        helpMenu.addActionListener(this);

        helpMenu.add(aboutMe);


        window.setJMenuBar(menuBar);




        top     = new JPanel();
        bottom  = new JPanel();
        left    = new JPanel();
        right   = new JPanel();
        content = new JPanel();

        top.setBackground(Color.MAGENTA);
        bottom.setBackground(Color.BLUE);
        left.setBackground(Color.GREEN);
        right.setBackground(Color.ORANGE);
        content.setBackground(Color.BLACK);

        window.add(top, BorderLayout.NORTH);
        window.add(bottom, BorderLayout.SOUTH);
        window.add(left, BorderLayout.EAST);
        window.add(right, BorderLayout.WEST);
        window.add(content, BorderLayout.CENTER);

        a.setText("Input 1");
        a.setBounds(0, 0, 200, 20);
        
        b.setText("Input 2");
        b.setBounds(0, 30, 200, 20);

        top.add(a);
        top.add(b);

        d.setBounds(300, 300, 70, 20);
        d.addActionListener(this);
        d.setText("Button");
        d.setFocusable(false);

        top.add(d);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == d) {
            System.out.println("Hello World!!!");
        }

        if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }
}
