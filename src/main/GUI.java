package main;

import javax.swing.*;

import mortages.*;

public class GUI{
    private JPanel panel;
    private JFrame frame;

    public GUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        panel.setLayout(null);
        
        frame.setVisible(true);
    }
}
