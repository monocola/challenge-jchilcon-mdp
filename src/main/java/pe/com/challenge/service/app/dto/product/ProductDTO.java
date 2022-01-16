package pe.com.challenge.service.app.dto.product;

import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String categoryName;
    private float unityPrice;
    private Integer state;
    private String createdAt;
}
