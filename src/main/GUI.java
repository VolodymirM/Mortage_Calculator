package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mortages.*;

public class GUI {

    private static JLabel loanLabel;
    private static JTextField loanText;
    private static JLabel euroLabel;
    
    private static JLabel percentLabel;
    private static JTextField percentText;
    private static JLabel percentSymbol;
    
    private static JLabel returnLabel;
    private static JTextField yearText;
    private static JLabel yearLabel;
    private static JTextField monthText;
    private static JLabel monthLabel;
    
    private static JCheckBox deferCheckBox;
    private static JLabel deferMonthLabel;
    private static JTextField deferMonthText;
    private static JLabel deferDurationLabel;
    private static JTextField deferDurationText;
    
    private static JLabel repaymentLabel;
    private static DefaultListModel<String> repaymentModel;
    private static JList<String> repaymenList;

    private static final String[] column = {"Month", "Amount(€)", "Portion", "Percente(%)", "Left to pay"};
    private static DefaultTableModel model = new DefaultTableModel(column, 0);
    private static JTable dataTable;
    private static JScrollPane scrollPane;

    private static JButton calculateButton;

    public GUI() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Mortage Calculator");
        frame.setSize(410,390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        panel.setLayout(null);
        
        // Loan amount
        loanLabel = new JLabel("Loan amount: ");
        loanLabel.setBounds(10, 10, 80, 25);
        panel.add(loanLabel);

        loanText = new JTextField("0.00");
        loanText.setBounds(95, 10, 80, 25);
        panel.add(loanText);

        euroLabel = new JLabel("€");
        euroLabel.setBounds(180, 10, 25, 25);
        panel.add(euroLabel);

        // Annual interest
        percentLabel = new JLabel("Annual interest: ");
        percentLabel.setBounds(234, 10, 100, 25);
        panel.add(percentLabel);

        percentText = new JTextField("0.00");
        percentText.setBounds(330, 10, 40, 25);
        panel.add(percentText);

        percentSymbol = new JLabel("%");
        percentSymbol.setBounds(375, 10, 25, 25);
        panel.add(percentSymbol);

        // Return term
        returnLabel = new JLabel("Return term: ");
        returnLabel.setBounds(10,40,80,25);
        panel.add(returnLabel);

        yearText = new JTextField("0");
        yearText.setBounds(95, 40, 25, 25);
        panel.add(yearText);

        yearLabel = new JLabel("years");
        yearLabel.setBounds(125, 40, 40, 25);
        panel.add(yearLabel);

        monthText = new JTextField("0");
        monthText.setBounds(165, 40, 25, 25);
        panel.add(monthText);

        monthLabel = new JLabel("months");
        monthLabel.setBounds(195, 40, 45, 25);
        panel.add(monthLabel);

        // Defer loan payment
        deferCheckBox = new JCheckBox("Defer loan payment");
        deferCheckBox.setBounds(10, 70, 135, 25);
        panel.add(deferCheckBox);

        deferMonthLabel = new JLabel("Deferment month: ");
        deferMonthLabel.setBounds(220, 70, 105, 25);
        panel.add(deferMonthLabel);

        deferMonthText = new JTextField("0");
        deferMonthText.setBounds(330, 70, 40, 25);
        deferMonthText.setEditable(false);
        panel.add(deferMonthText);

        deferDurationLabel = new JLabel("Deferment duration: ");
        deferDurationLabel.setBounds(208, 100, 120, 25);
        panel.add(deferDurationLabel);

        deferDurationText = new JTextField("0");
        deferDurationText.setBounds(330, 100, 40, 25);
        deferDurationText.setEditable(false);
        panel.add(deferDurationText);
        
        // Repayment schedule
        repaymentLabel = new JLabel("Repayment schedule: ");
        repaymentLabel.setBounds(10, 145, 125, 25);
        panel.add(repaymentLabel);
        
        repaymentModel = new DefaultListModel<>();
        repaymentModel.addElement("Annuity");
        repaymentModel.addElement("Linear");
        repaymenList = new JList<>(repaymentModel);
        repaymenList.setBounds(140, 140, 50, 36);
        panel.add(repaymenList);

        // Data table
        dataTable = new JTable(model);
        dataTable.setDefaultEditor(Object.class, null);
        dataTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(14, 190, 370, 150);
        panel.add(scrollPane);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(270, 140, 100, 36);
        panel.add(calculateButton);

        frame.setVisible(true);
    }
}
