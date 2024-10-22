package mortages;

public class Annuity extends Mortage {

    public Annuity(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
        super(loanAmount, annualInterest, returnYears, returnMonths, defermentMonth, defermentTerm);
    }

    @Override
    public String[][] Calculate() {
        
        String[][] data = new String[returnYears * 12 + returnMonths][5];

        double willBePayed = calculateLoanAmount();
        double mountlyPayment = willBePayed / (returnYears * 12 + returnMonths);
        double portion = mountlyPayment / willBePayed;
        double percentage = portion * 100;
        
        for (int i = 0; i < data.length; ++i) {
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = String.format("%.2f", mountlyPayment);
            data[i][2] = String.format("%.4f", portion);
            data[i][3] = String.format("%.2f", percentage) + " %";
            data[i][4] = String.format("%.2f", willBePayed - mountlyPayment * (i + 1));
        }

        return data;
    }

    @Override
    public String[][] CalculateWithDeferement() {
        
        String[][] data = new String[returnYears * 12 + returnMonths + defermentTerm][5];

        double willBePayed = calculateLoanAmount() + loanAmount * defermentMonthFine * defermentTerm;
        double mountlyPayment = willBePayed / (returnYears * 12 + returnMonths);
        double portion = mountlyPayment / willBePayed;
        double percentage = portion * 100;

        int helpIndex = 0;

        for (int i = 0; i < data.length; ++i) {

            if (i == defermentMonth - 1) {
                for (int t = i; t < i + defermentTerm && t < data.length; ++t) {
                    data[t][0] = Integer.toString(t + 1);
                    data[t][1] = "-";
                    data[t][2] = "-";
                    data[t][3] = "-";
                    data[t][4] = data[i - 1][4];
                }

                i += defermentTerm;
                helpIndex = defermentTerm;

            }

            if (i < data.length) {
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = String.format("%.2f", mountlyPayment);
                data[i][2] = String.format("%.4f", portion);
                data[i][3] = String.format("%.2f", percentage) + " %";
                data[i][4] = String.format("%.2f", willBePayed - mountlyPayment * (i - helpIndex + 1));
            }
        }

        return data;
    }

    @Override
    protected double calculateLoanAmount() {
        return (loanAmount + loanAmount * annualInterest * returnYears / 100);
    }

}
