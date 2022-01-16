package pe.com.challenge.service.app.bussines.impl;

import io.swagger.client.model.ProductResponse;
import io.swagger.client.model.ProductResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.challenge.service.app.bussines.IProductService;
import pe.com.challenge.service.app.dto.product.ProductDTO;
import pe.com.challenge.service.app.entity.Product;
import pe.com.challenge.service.app.repository.ProductRepository;
import pe.com.challenge.service.app.util.Constant;

import java.sql.Timestamp;
import java.util.*;

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
            productResponseBody.setState(p.getState());
            productResponseBody.setCreatedAt(p.getCreatedAt().toString());
            productResponseBodyArrayList.add(productResponseBody);

        });

        productResponse.setProducts(productResponseBodyArrayList);
        return productResponse;
    }

    @Override
    public Product createProduct(Product product) {
        product.setState(Constant.INTEGER_ESTADO_ACTIVO);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> findProductById(UUID productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductDTO findProductDTOById(UUID productId) {
        ProductDTO productDTO = new ProductDTO();
        Product prodEntity = productRepository.findProductEntityById(productId);
        productDTO.setId(prodEntity.getId().toString());
        productDTO.setName(prodEntity.getName());
        productDTO.setCategoryName(prodEntity.getCategoryName());
        productDTO.setUnityPrice(prodEntity.getUnityPrice());
        productDTO.setState(prodEntity.getState());
        productDTO.setCreatedAt(prodEntity.getCreatedAt().toString());
        return productDTO;
    }

    @Override
    public void updateProduct(Product product) {
        UUID productId = UUID.fromString(product.getId().toString());
        Product productObj = productRepository.findProductEntityById(productId);
        productObj.setName(product.getName());
        productObj.setCategoryName(product.getCategoryName());
        productObj.setUnityPrice(product.getUnityPrice());
        productObj.setState(Constant.INTEGER_ESTADO_ACTIVO);
        Long datetime = System.currentTimeMillis();
        Timestamp date = new Timestamp(datetime);
        productObj.setCreatedAt(date);
        productRepository.save(productObj);
    }
}
