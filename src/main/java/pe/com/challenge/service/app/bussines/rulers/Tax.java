package pe.com.challenge.service.app.bussines.rulers;

import pe.com.challenge.service.app.util.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * clase  que define las reglas de negocio para impuestos. <br/>
 * <b>Clases Implementadoras</b>: OrderServiceImpl <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */
public class Tax {

    /**
     * Metodo que permite genera numero de orden aleatório.
     * @Param null
     * @return String
     */
    public static String generateOrderNumber(){
        int min_val = 1001;
        int max_val = 9999;
        double randomNum = Math.random() * ( max_val - min_val );
        Integer orderNumber = (int) randomNum;
        return String.valueOf(orderNumber);
    }

    /**
     * Metodo que permite calcular el impuesto municipal.
     * @Param String subTotalString
     * @return String
     */
    public static String getTotalCityTax(String subTotalString){
     float subTotal = Float.valueOf(subTotalString);
     float subTotalAmount = (float) (subTotal * Constant.FLOAT_0_10);
     BigDecimal subTotalA = BigDecimal.valueOf(subTotalAmount).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
     return String.valueOf(subTotalA);
    }

    /**
     * Metodo que permite calcular el impuesto del condado.
     * @Param String subTotalString, totalCityTaxString
     * @return String
     */
    public static String getTotalCountTax(String subTotalString, String totalCityTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float subTotalCountyTaxAmount = (float) ((subTotal + totalCityTax) * Constant.FLOAT_0_05);
        BigDecimal totalCounty = BigDecimal.valueOf(subTotalCountyTaxAmount).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
        return String.valueOf(totalCounty);
    }

    /**
     * Metodo que permite calcular el impuesto estatal.
     * @Param String subTotalString, totalCityTaxString, subTotalCountyTaxString
     * @return String
     */
    public static String getTotalStateTax(String subTotalString, String totalCityTaxString, String subTotalCountyTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTaxString);
        float subTotalCountyTaxAmount = (float) ((subTotal + totalCityTax + totalCountyTax));
        float totalStateTax = (float) (subTotalCountyTaxAmount * Constant.FLOAT_0_08);
        BigDecimal totalTax = BigDecimal.valueOf(totalStateTax).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
        return String.valueOf(totalTax);
    }

    /**
     * Metodo que permite calcular el impuesto federal.
     * @Param String subTotalString, totalCityTaxString, subTotalCountyTax, getTotalStateTaxString
     * @return String
     */
    public static String getTotalFederalTax(String subTotalString, String totalCityTaxString, String subTotalCountyTax, String getTotalStateTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTax);
        float totalStateTaxString = Float.valueOf(getTotalStateTaxString);
        float totalFederalTax = (float) ((subTotal + totalCityTax + totalCountyTax + totalStateTaxString));
        float totalStateTax = (float) (totalFederalTax * Constant.FLOAT_0_02);
        BigDecimal totalState = BigDecimal.valueOf(totalStateTax).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
        return String.valueOf(totalState);
    }

    /**
     * Metodo que permite calcular el impuesto total.
     * @Param String totalCityTaxString, subTotalCountyTax, getTotalStateTaxString, getTotalFederalTaxString
     * @return String
     */
    public static String getTotalTax(String totalCityTaxString, String subTotalCountyTax, String getTotalStateTaxString, String getTotalFederalTaxString){
        float totalCityTax = Float.valueOf(totalCityTaxString);
        float totalCountyTax = Float.valueOf(subTotalCountyTax);
        float totalStateTaxString = Float.valueOf(getTotalStateTaxString);
        float totalFederalTaxString = Float.valueOf(getTotalFederalTaxString);
        float totalTaxs = (float) ((totalCityTax + totalCountyTax + totalStateTaxString + totalFederalTaxString));
        BigDecimal totalTax = BigDecimal.valueOf(totalTaxs).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
        return String.valueOf(totalTax);
    }

    /**
     * Metodo que permite calcular el monto total.
     * @Param String subTotalString, getTotalTaxString
     * @return String
     */
    public static String getTotal(String subTotalString, String getTotalTaxString){
        float subTotal = Float.valueOf(subTotalString);
        float totalTax = Float.valueOf(getTotalTaxString);
        float totalAmount = (float) (subTotal + totalTax);
        BigDecimal subTotalA = BigDecimal.valueOf(totalAmount).setScale(Constant.INTEGER_DOS, RoundingMode.HALF_DOWN);
        return String.valueOf(subTotalA);
    }

}
