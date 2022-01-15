package pe.com.challenge.service.app.dto.order;

import io.swagger.client.model.OrderDetail;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
