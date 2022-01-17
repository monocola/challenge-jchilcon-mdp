package pe.com.challenge.service.app.dto.orderdetail;

import lombok.Data;

/**
 * Objeto DTO define los atributos necesario para mostrar detalle de ordenes. <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 14, 2022.
 */
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
