package mortages;

import data.Data;

public class Linear extends Mortage {

    public Linear(Data data) {
        super(data);
    }

    @Override
    public String[][] Calculate() {
        
        String[][] dataStringArray = new String[data.getReturnYears() * 12 + data.getReturnMonths()][5];

        double willBePayed = calculateLoanAmount();
        double mainMountlyPayment = data.getLoanAmount() / (data.getReturnYears()* 12 + data.getReturnMonths());
        double alreadyPaid = 0;

        for (int i = 0; i < dataStringArray.length; ++i) {

            double mountlyPayment = mainMountlyPayment + (data.getLoanAmount() - mainMountlyPayment * i) * (data.getAnnualInterest()  / 100 / 12);
            alreadyPaid += mountlyPayment;

            dataStringArray[i][0] = Integer.toString(i + 1);
            dataStringArray[i][1] = String.format("%.2f", mountlyPayment);
            dataStringArray[i][2] = String.format("%.4f", mountlyPayment / willBePayed);
            dataStringArray[i][3] = String.format("%.2f", mountlyPayment / willBePayed * 100) + " %";
            dataStringArray[i][4] = String.format("%.2f", willBePayed - alreadyPaid);
        }

        return dataStringArray;
    }

    @Override
    public String[][] CalculateWithDeferement() {
        
        String[][] dataStringArray = new String[data.getReturnYears() * 12 + data.getReturnMonths() + data.getDefermentTerm()][5];

        double fee = data.getLoanAmount() * defermentMonthFine * data.getDefermentTerm();
        double willBePayed = calculateLoanAmount() + fee;
        double mainMountlyPayment = data.getLoanAmount() / (data.getReturnYears() * 12 + data.getReturnMonths());
        double alreadyPaid = 0;
        int index = 0;
        boolean defered = false;

        for (int i = 0; i < dataStringArray.length; ++i) {

            if (i == data.getDefermentMonth() - 1) {
                for (int t = i; t < i + data.getDefermentTerm() && t < dataStringArray.length; ++t) {
                    dataStringArray[t][0] = Integer.toString(t + 1);
                    dataStringArray[t][1] = "-";
                    dataStringArray[t][2] = "-";
                    dataStringArray[t][3] = "-";
                    dataStringArray[t][4] = String.format("%.2f", willBePayed - alreadyPaid);
                }
                index = i;
                defered = true;
                i += data.getDefermentTerm();
            }

            if (i < dataStringArray.length) {

                double mountlyPayment;

                if (defered){
                    mountlyPayment = mainMountlyPayment + (data.getLoanAmount() - mainMountlyPayment * index)
                     * (data.getAnnualInterest() / 100 / 12) + fee / (data.getReturnYears() * 12 + data.getReturnMonths());
                    ++index;
                }
                else
                    mountlyPayment = mainMountlyPayment + (data.getLoanAmount() - mainMountlyPayment * i)
                * (data.getAnnualInterest() / 100 / 12) + fee / (data.getReturnYears() * 12 + data.getReturnMonths());
                alreadyPaid += mountlyPayment;
                
                dataStringArray[i][0] = Integer.toString(i + 1);
                dataStringArray[i][1] = String.format("%.2f", mountlyPayment);
                dataStringArray[i][2] = String.format("%.4f", mountlyPayment / willBePayed);
                dataStringArray[i][3] = String.format("%.2f", mountlyPayment / willBePayed * 100) + " %";
                dataStringArray[i][4] = String.format("%.2f", Math.abs(willBePayed - alreadyPaid));
            }
        }

        

        return dataStringArray;
    }

    @Override
    protected double calculateLoanAmount() {
        return (data.getLoanAmount() + ((data.getLoanAmount() * (data.getAnnualInterest() / 100)) / 12) * ((double) (data.getReturnYears() * 12 + data.getReturnMonths() + 1) / 2));
    }

}
