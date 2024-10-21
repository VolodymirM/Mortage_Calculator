package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import mortages.*;

public class GUI {
    // UI components
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
    private static JList<String> repaymentList;

    private static final String[] column = {"Month", "Amount(€)", "Portion", "Percente(%)", "Left to pay"};
    private static DefaultTableModel model = new DefaultTableModel(column, 0);
    private static JTable dataTable;
    private static JScrollPane scrollPane;

    private static JButton calculateButton;

    // Internal variables
    private static boolean isDefering = false;
    private static String scheduleModel = "";
    private static Mortage mortage;
    private static double loanAmount = 0.0;
    private static double annualInterest = 0.0;
    private static int returnYears = 0;
    private static int returnMonths = 0;
    private static int defermentMonth = 0;
    private static int defermentTerm = 0;
    private static String[][] data;

    private static void setData(String[][] data) {
        GUI.data = data;
    }

    private void changeTablesData(String[][] data) {
        //TODO tables change's logic
    }

    private boolean checkDefermentValues() {
        if (isDefering && ((defermentMonth == 0) || (defermentMonth > (returnYears * 12 + returnMonths)) || defermentTerm < 1))
            return false;
        return true;
    }

    

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

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setEnabled(false);
        calculateButton.setBounds(270, 140, 100, 36);
        panel.add(calculateButton);
        
        // Repayment schedule
        repaymentLabel = new JLabel("Repayment schedule: ");
        repaymentLabel.setBounds(10, 145, 125, 25);
        panel.add(repaymentLabel);
        
        repaymentModel = new DefaultListModel<>();
        repaymentModel.addElement("Annuity");
        repaymentModel.addElement("Linear");
        repaymentList = new JList<>(repaymentModel);
        repaymentList.setBounds(140, 140, 50, 36);
        panel.add(repaymentList);

        repaymentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                calculateButton.setEnabled(true);
                scheduleModel = repaymentList.getSelectedValue();
                System.out.println(scheduleModel + " selected");
                
            }
        });

        // Data table
        dataTable = new JTable(model);
        dataTable.setDefaultEditor(Object.class, null);
        dataTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(14, 190, 370, 150);
        panel.add(scrollPane);

        frame.setVisible(true);
        
        // Action listeners
        deferCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                isDefering = !isDefering;
                if (isDefering) {
                    deferMonthText.setEditable(true);
                    deferDurationText.setEditable(true);
                }
                else {
                    deferMonthText.setEditable(false);
                    deferDurationText.setEditable(false);
                }
            }
        });
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loanAmount = Double.parseDouble(loanText.getText());
                    annualInterest = Double.parseDouble(percentText.getText());
                    returnYears = Integer.parseInt(yearText.getText());
                    returnMonths = Integer.parseInt(monthText.getText());

                    if (isDefering) {
                        defermentMonth = Integer.parseInt(deferMonthText.getText());
                        defermentTerm = Integer.parseInt(deferDurationText.getText());
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Wrong format of the entered data.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong format");
                    return;
                }
                
                if (checkDefermentValues() == false) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Check deferement values.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong values");
                    return;
                }
                
                if (scheduleModel == "Annuity") {
                    mortage = new Annuity(loanAmount, annualInterest, returnYears, returnMonths, defermentMonth, defermentTerm);
                    System.out.println("Annuity");
                } else if (scheduleModel == "Linear") {
                    mortage = new Linear(loanAmount, annualInterest, returnYears, returnMonths, defermentMonth, defermentTerm);
                    System.out.println("Linear");
                }
                
                model.setRowCount(0);

                if (isDefering) {
                    String[][] calctulatedData = mortage.CalculateWithDeferement();
                    setData(calctulatedData);
                }
                else {
                    String[][] calctulatedData = mortage.Calculate();
                    setData(calctulatedData);
                }
                
                // TODO Calculation, table's changing
                changeTablesData(data);
                System.out.println("Calculated");
            } 
        });
    }

}
