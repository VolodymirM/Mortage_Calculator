package mortages;

public class Linear extends Mortage {

    public Linear(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
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
            data[i][1] = Double.toString(mountlyPayment);
            data[i][2] = Double.toString(portion);
            data[i][3] = (Double.toString(percentage) + " %");
            data[i][4] = Double.toString(willBePayed - mountlyPayment * (i + 1));
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
        
        int returnTerm = data.length;
        
        for (int i = 0; i < returnTerm; ++i) {

            if (i == defermentMonth - 1) {
                for (int t = i; t < i + defermentTerm && t < data.length; ++t) {
                    data[t][0] = Integer.toString(t + 1);
                    data[t][1] = "-";
                    data[t][2] = "-";
                    data[t][3] = "-";
                    data[t][4] = data[i - 1][4];
                }

                i += defermentTerm;

            }

            if (i < returnTerm) {
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = Double.toString(mountlyPayment);
                data[i][2] = Double.toString(portion);
                data[i][3] = (Double.toString(percentage) + " %");
                data[i][4] = Double.toString(willBePayed - mountlyPayment * (i + 1));

                //TODO Fix "left to pay" after deferemet
            }
        }

        return data;
    }

}
