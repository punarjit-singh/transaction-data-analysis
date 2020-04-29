package org.orgname.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class CalculationsHelper {

    public static Serializable calculateAverage(List<Double> prices, boolean asString) {
        DecimalFormat df = new DecimalFormat("0.00");
        double sum = 0;
        double avgPrice = 0;
        if(!prices.isEmpty()) {
            for (Double price : prices) {
                sum += price;
            }
            avgPrice = sum / prices.size();
        }
        
        if(asString) {
            return df.format(avgPrice);
        } else {
            return Double.parseDouble(df.format(avgPrice));
        }
    }

}
