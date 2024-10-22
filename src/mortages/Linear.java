package mortages;

public class Linear extends Mortage {

    public Linear(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
        super(loanAmount, annualInterest, returnYears, returnMonths, defermentMonth, defermentTerm);
    }

    @Override
    public String[][] Calculate() {
        
        String[][] data = new String[returnYears * 12 + returnMonths][5];

        double willBePayed = calculateLoanAmount();
        double mainMountlyPayment = loanAmount / (returnYears * 12 + returnMonths);
        double alreadyPaid = 0;

        for (int i = 0; i < data.length; ++i) {

            double mountlyPayment = mainMountlyPayment + (loanAmount - mainMountlyPayment * i) * (annualInterest / 100 / 12);
            alreadyPaid += mountlyPayment;

            data[i][0] = Integer.toString(i + 1);
            data[i][1] = String.format("%.2f", mountlyPayment);
            data[i][2] = String.format("%.4f", mountlyPayment / willBePayed);
            data[i][3] = String.format("%.2f", mountlyPayment / willBePayed * 100) + " %";
            data[i][4] = String.format("%.2f", willBePayed - alreadyPaid);
        }

        return data;
    }

    @Override
    public String[][] CalculateWithDeferement() {
        
        String[][] data = new String[returnYears * 12 + returnMonths + defermentTerm][5];

        double fee = loanAmount * defermentMonthFine * defermentTerm;
        double willBePayed = calculateLoanAmount() + fee;
        double mainMountlyPayment = loanAmount / (returnYears * 12 + returnMonths);
        double alreadyPaid = 0;
        int index = 0;
        boolean defered = false;

        for (int i = 0; i < data.length; ++i) {

            if (i == defermentMonth - 1) {
                for (int t = i; t < i + defermentTerm && t < data.length; ++t) {
                    data[t][0] = Integer.toString(t + 1);
                    data[t][1] = "-";
                    data[t][2] = "-";
                    data[t][3] = "-";
                    data[t][4] = String.format("%.2f", willBePayed - alreadyPaid);
                }
                index = i;
                defered = true;
                i += defermentTerm;
            }

            if (i < data.length) {

                double mountlyPayment;

                if (defered){
                    mountlyPayment = mainMountlyPayment + (loanAmount - mainMountlyPayment * index)
                     * (annualInterest / 100 / 12) + fee / (returnYears * 12 + returnMonths);
                    ++index;
                }
                else
                    mountlyPayment = mainMountlyPayment + (loanAmount - mainMountlyPayment * i)
                * (annualInterest / 100 / 12) + fee / (returnYears * 12 + returnMonths);
                alreadyPaid += mountlyPayment;
                
                data[i][0] = Integer.toString(i + 1);
                data[i][1] = String.format("%.2f", mountlyPayment);
                data[i][2] = String.format("%.4f", mountlyPayment / willBePayed);
                data[i][3] = String.format("%.2f", mountlyPayment / willBePayed * 100) + " %";
                data[i][4] = String.format("%.2f", Math.abs(willBePayed - alreadyPaid));
            }
        }

        

        return data;
    }

    @Override
    protected double calculateLoanAmount() {
        return (loanAmount + ((loanAmount * (annualInterest / 100)) / 12) * ((double) (returnYears * 12 + returnMonths + 1) / 2));
    }

}
