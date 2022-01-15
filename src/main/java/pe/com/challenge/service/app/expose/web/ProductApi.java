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
import pe.com.challenge.service.app.entity.Product;


@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
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
}
