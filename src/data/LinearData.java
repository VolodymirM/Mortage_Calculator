package data;

public class LinearData extends Data {
    
    public LinearData() {
        super();
    }

    public LinearData(Data dataOld) {
        this.loanAmount = dataOld.loanAmount;
        this.annualInterest = dataOld.annualInterest;
        this.returnYears = dataOld.returnYears;
        this.returnMonths = dataOld.returnMonths;
        this.defermentMonth = dataOld.defermentMonth;
        this.defermentTerm = dataOld.defermentTerm;
    }
}
