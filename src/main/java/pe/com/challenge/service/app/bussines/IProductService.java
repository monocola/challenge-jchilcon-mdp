package pe.com.challenge.service.app.bussines;

import io.swagger.client.model.ProductResponse;
import pe.com.challenge.service.app.entity.Product;

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
}
