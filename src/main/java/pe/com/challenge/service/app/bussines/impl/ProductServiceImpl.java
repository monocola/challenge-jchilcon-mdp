package pe.com.challenge.service.app.bussines.impl;

import io.swagger.client.model.ProductResponse;
import io.swagger.client.model.ProductResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.challenge.service.app.bussines.IProductService;
import pe.com.challenge.service.app.entity.Product;
import pe.com.challenge.service.app.repository.ProductRepository;
import pe.com.challenge.service.app.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * clase implementadora que define los métodos necesario para listar productos <br/>
 * <b>Interface</b>: IProductService <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public ProductResponse getProducts() {

        ProductResponse productResponse = new ProductResponse();
        ArrayList<ProductResponseBody> productResponseBodyArrayList = new ArrayList<>();

        List<Product> productList = productRepository.findAll();
        productList.forEach(p ->{
            ProductResponseBody productResponseBody = new ProductResponseBody();
            productResponseBody.setId(p.getId().toString());
            productResponseBody.setCategoryName(p.getCategoryName());
            productResponseBody.setName(p.getName());
            productResponseBody.setUnityPrice(p.getUnityPrice());
            productResponseBody.setState(Constant.INTEGER_ESTADO_ACTIVO);
            productResponseBody.setCreatedAt(p.getCreatedAt().toString());
            productResponseBodyArrayList.add(productResponseBody);

        });

        productResponse.setProducts(productResponseBodyArrayList);
        return productResponse;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
