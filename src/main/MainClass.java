package main;

import javax.swing.SwingUtilities;

public class MainClass {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
