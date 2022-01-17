package pe.com.challenge.service.app.expose.web;


import io.reactivex.Maybe;
import io.reactivex.Single;
import io.swagger.client.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IProductService;
import pe.com.challenge.service.app.dto.product.ProductDTO;
import pe.com.challenge.service.app.entity.Product;
import pe.com.challenge.service.app.exeption.BeanNotFoundException;
import pe.com.challenge.service.app.util.Constant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * clase controller que define los métodos necesario para listar productos <br/>
 * <b>Interface</b>: iProductService <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(origins = {"http://localhost:4200", "*"}, maxAge = 3500, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductApi {

    @Autowired
    private IProductService iProductService;

    @GetMapping
    public Maybe<ResponseEntity<ProductResponse>> getProducts() {
        return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iProductService.getProducts()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<Product> create(@RequestBody Product product) {
        return Single.just(iProductService.createProduct(product));

    }

    @DeleteMapping("/{productid}")
    public Single<ResponseEntity<Void>> deleteProductInOrder(@PathVariable UUID productid){
        if(!iProductService.findProductById(productid).isPresent()) {
            return Single.just(ResponseEntity.notFound().build());
        }
        iProductService.deleteProduct(productid);
        return Single.just(ResponseEntity.ok().build());
    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<ProductDTO>> getOrderById(@PathVariable UUID id) {
        Map<String,Object> respuesta = new HashMap<String, Object>();
        return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iProductService.findProductDTOById(id))).doOnError(e ->{
            throw new BeanNotFoundException(Constant.MESSAGE_NO_CONTENT);

        });
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product) {
        try{
            iProductService.updateProduct(product);
        }catch (Exception e){
            throw new BeanNotFoundException(Constant.MESSAGE_NO_FOUND);
        }

    }
}
