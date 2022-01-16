package pe.com.challenge.service.app.bussines;

import io.swagger.client.model.ProductResponse;
import pe.com.challenge.service.app.dto.product.ProductDTO;
import pe.com.challenge.service.app.entity.Product;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface service que define los métodos necesario para listar productos <br/>
 * <b>Class</b>: ProductServiceImpl <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */

public interface IProductService {

     ProductResponse getProducts();

     Product createProduct(Product product);

     void deleteProduct(UUID productId);

     Optional<Product> findProductById(UUID productId);

     ProductDTO findProductDTOById(UUID productId);

     void updateProduct(Product product);
}
