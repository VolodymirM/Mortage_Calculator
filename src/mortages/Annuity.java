package mortages;

import data.Data;

public class Annuity extends Mortage {

    public Annuity(Data data) {
        super(data);
    }

    @Override
    public String[][] Calculate() {
        
        String[][] dataStringArray = new String[data.getReturnYears() * 12 + data.getReturnMonths()][5];

        double willBePayed = calculateLoanAmount();
        double mountlyPayment = willBePayed / (data.getReturnYears() * 12 + data.getReturnMonths());
        double portion = mountlyPayment / willBePayed;
        double percentage = portion * 100;
        
        for (int i = 0; i < dataStringArray.length; ++i) {
            dataStringArray[i][0] = Integer.toString(i + 1);
            dataStringArray[i][1] = String.format("%.2f", mountlyPayment);
            dataStringArray[i][2] = String.format("%.4f", portion);
            dataStringArray[i][3] = String.format("%.2f", percentage) + " %";
            dataStringArray[i][4] = String.format("%.2f", willBePayed - mountlyPayment * (i + 1));
        }

        return dataStringArray;
    }

    @Override
    public String[][] CalculateWithDeferement() {
        
        String[][] dataStringArray = new String[data.getReturnYears() * 12 + data.getReturnMonths() + data.getDefermentTerm()][5];

        double willBePayed = calculateLoanAmount() + data.getLoanAmount() * defermentMonthFine * data.getDefermentTerm();
        double mountlyPayment = willBePayed / (data.getReturnYears() * 12 + data.getReturnMonths());
        double portion = mountlyPayment / willBePayed;
        double percentage = portion * 100;

        int helpIndex = 0;

        for (int i = 0; i < dataStringArray.length; ++i) {

            if (i == data.getDefermentMonth() - 1) {
                for (int t = i; t < i + data.getDefermentTerm() && t < dataStringArray.length; ++t) {
                    dataStringArray[t][0] = Integer.toString(t + 1);
                    dataStringArray[t][1] = "-";
                    dataStringArray[t][2] = "-";
                    dataStringArray[t][3] = "-";
                    dataStringArray[t][4] = dataStringArray[i - 1][4];
                }

                helpIndex = data.getDefermentTerm();
                i += helpIndex;

            }

            if (i < dataStringArray.length) {
                dataStringArray[i][0] = Integer.toString(i + 1);
                dataStringArray[i][1] = String.format("%.2f", mountlyPayment);
                dataStringArray[i][2] = String.format("%.4f", portion);
                dataStringArray[i][3] = String.format("%.2f", percentage) + " %";
                dataStringArray[i][4] = String.format("%.2f", willBePayed - mountlyPayment * (i - helpIndex + 1));
            }
        }

        return dataStringArray;
    }

    @Override
    protected double calculateLoanAmount() {
        return (data.getLoanAmount() + data.getLoanAmount() * data.getAnnualInterest() * data.getReturnYears() / 100);
    }

}
