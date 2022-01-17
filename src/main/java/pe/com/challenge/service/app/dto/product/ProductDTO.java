package pe.com.challenge.service.app.dto.product;

import lombok.Data;

/**
 * Objeto DTO define los atributos necesario para mostrar producto. <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 14, 2022.
 */
@Data
public class ProductDTO {
    private String id;
    private String name;
    private String categoryName;
    private float unityPrice;
    private Integer state;
    private String createdAt;
}
