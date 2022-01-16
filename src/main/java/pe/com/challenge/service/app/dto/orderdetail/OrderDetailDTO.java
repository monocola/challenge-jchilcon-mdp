package pe.com.challenge.service.app.dto.orderdetail;

import lombok.Data;

@Data
public class OrderDetailDTO {
    public String orderDetailId;
    public String productId;
    public Object productName;
    public String quantity;
    public String unityPrice;
    public String totalAmount;
    public String createdAt;
}
