// Vladimir Moskalyuk

package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import mortages.*;
import data.*;

public class Menu {
    // UI components
    private static JPanel panel;
    private static JFrame frame;

    private static JLabel label; // Composite structural design pattern
    
    private static JTextField loanText;
    private static JTextField percentText;
    private static JTextField yearText;
    private static JTextField monthText;
    private static JCheckBox deferCheckBox;
    private static JTextField deferMonthText;
    private static JTextField deferDurationText;
    private static DefaultListModel<String> repaymentModel;
    private static JList<String> repaymentList;

    private static final String[] column = {"Month", "Amount(€)", "Portion", "Percente(%)", "Left to pay"};
    private static DefaultTableModel model = new DefaultTableModel(column, 0);
    private static JTable dataTable;
    private static JScrollPane scrollPane;

    private static JButton calculateButton;

    private static JButton showGraphButton;

    // Internal variables
    private static boolean isDefering = false;
    private static String scheduleModel = "";
    private static Mortage mortage;
    private static Data data;

    private void changeTablesData() {
        
        String[][] dataStringArray = data.getDataTable();

        model.setRowCount(0);

        for (int i = 0; i < dataStringArray.length; ++i)
            model.addRow(new Object[]{dataStringArray[i][0], dataStringArray[i][1], dataStringArray[i][2], dataStringArray[i][3], dataStringArray[i][4]});
    }

    public Menu() {
        data = new Data();

        panel = new JPanel();
        frame = new JFrame("Mortage Calculator");
        frame.setSize(410,390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        panel.setLayout(null);
        
        // Loan amount
        label = new JLabel("Loan amount: ");
        label.setBounds(10, 10, 80, 25);
        panel.add(label);

        loanText = new JTextField("0.00");
        loanText.setBounds(95, 10, 80, 25);
        panel.add(loanText);

        label = new JLabel("€");
        label.setBounds(180, 10, 25, 25);
        panel.add(label);

        // Annual interest
        label = new JLabel("Annual interest: ");
        label.setBounds(234, 10, 100, 25);
        panel.add(label);

        percentText = new JTextField("0.00");
        percentText.setBounds(330, 10, 40, 25);
        panel.add(percentText);

        label = new JLabel("%");
        label.setBounds(375, 10, 25, 25);
        panel.add(label);

        // Return term
        label = new JLabel("Return term: ");
        label.setBounds(10,40,80,25);
        panel.add(label);

        yearText = new JTextField("0");
        yearText.setBounds(95, 40, 25, 25);
        panel.add(yearText);

        label = new JLabel("years");
        label.setBounds(125, 40, 40, 25);
        panel.add(label);

        monthText = new JTextField("0");
        monthText.setBounds(165, 40, 25, 25);
        panel.add(monthText);

        label = new JLabel("months");
        label.setBounds(195, 40, 45, 25);
        panel.add(label);

        // Defer loan payment
        deferCheckBox = new JCheckBox("Defer loan payment");
        deferCheckBox.setBounds(10, 70, 135, 25);
        panel.add(deferCheckBox);

        label = new JLabel("Deferment month: ");
        label.setBounds(220, 70, 105, 25);
        panel.add(label);

        deferMonthText = new JTextField("0");
        deferMonthText.setBounds(330, 70, 40, 25);
        deferMonthText.setEditable(false);
        panel.add(deferMonthText);

        label = new JLabel("Deferment duration: ");
        label.setBounds(208, 100, 120, 25);
        panel.add(label);

        deferDurationText = new JTextField("0");
        deferDurationText.setBounds(330, 100, 40, 25);
        deferDurationText.setEditable(false);
        panel.add(deferDurationText);
        
        // Repayment schedule
        label = new JLabel("Repayment schedule: ");
        label.setBounds(10, 145, 125, 25);
        panel.add(label);
        
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

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setEnabled(false);
        calculateButton.setBounds(280, 140, 90, 36);
        panel.add(calculateButton);
        
        // Show graph
        showGraphButton = new JButton("Graph");
        showGraphButton.setEnabled(false);
        showGraphButton.setBounds(200, 140, 70, 36);
        panel.add(showGraphButton);
        
        frame.setVisible(true);
        
        // Action listeners - !Command behavioral pattern!
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
                    data.setLoanAmount(Double.parseDouble(loanText.getText()));
                    data.setAnnualInterest(Double.parseDouble(percentText.getText()));
                    data.setReturnYears(Integer.parseInt(yearText.getText()));
                    data.setReturnMonths(Integer.parseInt(monthText.getText()));

                    if (isDefering) {
                        data.setDefermentMonth(Integer.parseInt(deferMonthText.getText()));
                        data.setDefermentTerm(Integer.parseInt(deferDurationText.getText()));
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Wrong format of the entered data.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong format");
                    return;
                }
                
                if (data.checkPositives() == false) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Wrong format of the entered data.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong format");
                    return;
                }

                if (data.checkDefermentValues(isDefering) == false) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Check deferement values.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong values");
                    return;
                }

                if (data.checkReturnTerm() == false) {
                    JOptionPane.showMessageDialog(null, "Calculation error! Check return term values.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong return term values");
                    return;
                }
                
                if (scheduleModel == "Annuity") {
                    mortage = new Annuity(data);
                    System.out.println("Annuity");
                } else if (scheduleModel == "Linear") {
                    mortage = new Linear(data);
                    System.out.println("Linear");
                }
                
                model.setRowCount(0);

                if (isDefering) 
                    data.setDataTable(mortage.CalculateWithDeferement());
                else 
                    data.setDataTable(mortage.Calculate());

                changeTablesData();
                showGraphButton.setEnabled(true);
                System.out.println("Calculated");
            }

        });

        showGraphButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showGraph();
            }
            
        });
    }

    private static void showGraph() {
        new LinearGraph(frame, frame.getLocation(), data);
        frame.setVisible(false);
        return;
    }

}
