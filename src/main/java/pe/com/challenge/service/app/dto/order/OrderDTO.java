package pe.com.challenge.service.app.dto.order;

import io.swagger.client.model.OrderDetail;
import lombok.Data;

import java.util.List;

/**
 * Objeto DTO define los atributos necesario para mostrar una orden. <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 14, 2022.
 */
@Data
public class OrderDTO {

    private String id;
    private String orderNumber;
    private String customer;
    private Integer state;
    private String createdAt;
    private String subTotal;
    private String totalCityTax;
    private String totalCountTax;
    private String totalStateTax;
    private String totalFederalTax;
    private String totalTax;
    private String total;
    private List<OrderDetail> orderDetail;

}
