package pe.com.challenge.service.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static String generateOrderNumber(){
        int min_val = 1001;
        int max_val = 9999;
        double randomNum = Math.random() * ( max_val - min_val );
        return String.valueOf(randomNum);
    }

    public static String getTotalCityTax(String subTotalString){
     float subTotal = Float.valueOf(subTotalString);
     float subTotalAmount = (float) (subTotal * 0.10);
     BigDecimal subTotalA = BigDecimal.valueOf(subTotalAmount).setScale(2, RoundingMode.HALF_DOWN);
     return String.valueOf(subTotalA);
    }

    public static String getTotalCountTax(String subTotalString, String totalCityTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float subTotalCountyTaxAmount = (float) ((subTotal + totalCityTax) * 0.05);
        BigDecimal totalCounty = BigDecimal.valueOf(subTotalCountyTaxAmount).setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(totalCounty);
    }

    public static String getTotalStateTax(String subTotalString, String totalCityTaxString, String subTotalCountyTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTaxString);
        float subTotalCountyTaxAmount = (float) ((subTotal + totalCityTax + totalCountyTax));
        float totalStateTax = (float) (subTotalCountyTaxAmount * 0.08);
        BigDecimal totalTax = BigDecimal.valueOf(totalStateTax).setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(totalTax);
    }

    public static String getTotalFederalTax(String subTotalString, String totalCityTaxString, String subTotalCountyTax, String getTotalStateTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTax);
        float totalStateTaxString = Float.valueOf(getTotalStateTaxString);
        float totalFederalTax = (float) ((subTotal + totalCityTax + totalCountyTax + totalStateTaxString));
        float totalStateTax = (float) (totalFederalTax * 0.02);
        BigDecimal totalState = BigDecimal.valueOf(totalStateTax).setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(totalState);
    }

    public static String getTotalTax(String totalCityTaxString, String subTotalCountyTax, String getTotalStateTaxString, String getTotalFederalTaxString){
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTax);
        float totalStateTaxString = Float.valueOf(getTotalStateTaxString);
        float totalFederalTaxString = Float.valueOf(getTotalFederalTaxString);
        float totalTaxs = (float) ((totalCityTax + totalCountyTax + totalStateTaxString + totalFederalTaxString));
        BigDecimal totalTax = BigDecimal.valueOf(totalTaxs).setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(totalTax);
    }

    public static String getTotal(String subTotalString, String getTotalTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalTax = Float.valueOf(getTotalTaxString);
        float totalAmount = (float) (subTotal + totalTax);
        BigDecimal subTotalA = BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_DOWN);
        return String.valueOf(subTotalA);
    }

}
