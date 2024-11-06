package data;

public class LinearData extends Data {
    
    private double xValues[];
    private double yValues[];

    public LinearData(Data dataOld) {
        convertOldData(dataOld);
            
    }
        
    private void convertOldData(Data dataOld) {
        String[][] dataTable = dataOld.getDataTable();

        xValues = new double[dataTable.length];
        yValues = new double[dataTable.length];

        for (int i = 0; i < dataTable.length; ++i) {
            xValues[i] = (double) (i + 1);

            if (dataTable[i][1] == "-")
                yValues[i] = 0.0;
            else {
                String valueStr = dataTable[i][1].replace(",", ".");
                try {
                    yValues[i] = Double.parseDouble(valueStr);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing value: " + dataTable[i][1]);
                    yValues[i] = 0.0; // Or handle it differently if necessary
                }
            }
        }
    }
        
    public double[] getXValues() {
        return xValues;
    }

    public double[] getYValues() {
        return yValues;
    }
}
