package data;

public class Data {

    private double loanAmount;
    private double annualInterest;
    private int returnYears;
    private int returnMonths;
    private int defermentMonth;
    private int defermentTerm;
    protected String[][] dataTable;

    public Data() {
        loanAmount = 0.0;
        annualInterest = 0.0;
        returnYears = 0;
        returnMonths = 0;
        defermentMonth = 0;
        defermentTerm = 0;
    }
    
    public Data(double loanAmount, double annualInterest, int returnYears, int returnMonths, int defermentMonth, int defermentTerm) {
        this.loanAmount = loanAmount;
        this.annualInterest = annualInterest;
        this.returnYears = returnYears;
        this.returnMonths = returnMonths;
        this.defermentMonth = defermentMonth;
        this.defermentTerm = defermentTerm;
    }
    
    public boolean checkPositives() {
        if (loanAmount <= 0 || annualInterest < 0 || returnYears < 0 || returnMonths < 0)
            return false;
        return true;
    } 

    public boolean checkDefermentValues(boolean isDefering) {
        if (isDefering && ((defermentMonth <= 0) || (defermentMonth > (returnYears * 12 + returnMonths)) || defermentTerm < 1))
            return false;
        return true;
    }

    public boolean checkReturnTerm() {
        if (returnMonths > 11 || returnYears < 0)
            return false;
        return true;
    }

    // Getters/setters
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

    public String[][] getDataTable() {
        return dataTable;
    }

    public void setDataTable(String[][] data) {
        this.dataTable = data;
    }
}
