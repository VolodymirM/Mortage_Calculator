// Factory creational pattern
package mortages;

import data.Data;

public abstract class Mortage {

    protected Data data;
    protected final double defermentMonthFine = 0.03;

    public Mortage(Data data) {
        this.data = data;
    }

    public abstract String[][] Calculate();
    public abstract String[][] CalculateWithDeferement();
    protected abstract double calculateLoanAmount();

}
