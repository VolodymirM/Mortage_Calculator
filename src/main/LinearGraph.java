package main;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import data.*;

public class LinearGraph {

    private static JButton backButton;
    private JFrame menuFrame;
    private LinearData data;

    public LinearGraph(JFrame menuFrame, Point location, Data dataOld) {
        
        this.data = new LinearData(dataOld);
        this.menuFrame = menuFrame;

        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Mortage Calculator");
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(location);

        panel.setLayout(null);

        // "Back" button
        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 62, 20);
        panel.add(backButton);

        frame.setVisible(true);

        // Action listeners
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.setVisible(false);
                menuFrame.setLocation(frame.getLocation());
                menuFrame.setVisible(true);
                return;
            }
            
        });
    }
}
