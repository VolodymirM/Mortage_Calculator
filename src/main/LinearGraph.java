package main;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import data.*;

public class LinearGraph {

    private static JPanel panel;
    private static JFrame frame;
    private static JButton backButton;
    private static JLabel linearGraphLabel;
    private LinearData data;

    public LinearGraph(JFrame menuFrame, Point location, Data dataOld) {
        
        this.data = new LinearData(dataOld);
        panel = new JPanel();
        frame = new JFrame("Mortage Calculator");
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(location);

        panel.setLayout(null);

        // "Payments' Linear graph" label
        linearGraphLabel = new JLabel("Payments' Linear graph");
        linearGraphLabel.setBounds(270, 25, 150, 25);
        panel.add(linearGraphLabel);

        // "Back" button
        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 62, 20);
        panel.add(backButton);

        GraphPanel graphPanel = new GraphPanel(this.data);
        graphPanel.setBounds(50, 50, 550, 300); // Adjust size and location as necessary
        panel.add(graphPanel);

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
