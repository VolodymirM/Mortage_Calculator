package mortages;

public class Linear extends Mortage {

    public Linear(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
        super(loanAmount, annualInterest, returnYears, returnMonths, defermentMonth, defermentTerm);
    }

    @Override
    public String[][] Calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Calculate'");
    }

    @Override
    public String[][] CalculateWithDeferement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CalculateWithDeferement'");
    }

}
