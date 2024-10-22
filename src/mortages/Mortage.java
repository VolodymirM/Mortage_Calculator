package mortages;

public abstract class Mortage {

    protected double loanAmount = 0.0;
    protected double annualInterest = 0.0;
    protected int returnYears = 0;
    protected int returnMonths = 0;
    protected int defermentMonth = 0;
    protected int defermentTerm = 0;
    protected final double defermentMonthFine = 0.03;

    public Mortage(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
        this.loanAmount = loanAmount;
        this.annualInterest = annualInterest;
        this.returnYears = returnYears;
        this.returnMonths = returnMonths;
        this.defermentMonth = defermentMonth;
        this.defermentTerm = defermentTerm;
    }

    public abstract String[][] Calculate();
    public abstract String[][] CalculateWithDeferement();
    protected abstract double calculateLoanAmount();

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getAnnualInterest() {
        return annualInterest;
    }

    public void setAnnualInterest(double annualInterest) {
        this.annualInterest = annualInterest;
    }

    public int getReturnYears() {
        return returnYears;
    }

    public void setReturnYears(int returnYears) {
        this.returnYears = returnYears;
    }

    public int getReturnMonths() {
        return returnMonths;
    }

    public void setReturnMonths(int returnMonths) {
        this.returnMonths = returnMonths;
    }

    public int getDefermentMonth() {
        return defermentMonth;
    }

    public void setDefermentMonth(int defermentMonth) {
        this.defermentMonth = defermentMonth;
    }

    public int getDefermentTerm() {
        return defermentTerm;
    }

    public void setDefermentTerm(int defermentTerm) {
        this.defermentTerm = defermentTerm;
    }
}
