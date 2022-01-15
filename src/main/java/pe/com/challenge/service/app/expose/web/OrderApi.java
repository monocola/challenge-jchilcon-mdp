package pe.com.challenge.service.app.expose.web;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.swagger.client.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.entity.Order;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderApi {

    @Autowired
    private IOrderService iOrderService;

    @GetMapping
    public Maybe<ResponseEntity<OrderResponse>> getOrders() {

        return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iOrderService.getOrders()));
    }

    @DeleteMapping("/eliminarproducto/{id}")
    public Single<ResponseEntity<Void>> deleteProductInOrder(@PathVariable UUID id){
        if(!iOrderService.getOrderDetailByID(id).isPresent()) {
            return Single.just(ResponseEntity.notFound().build());
        }
        iOrderService.deleteProduct(id);
        return Single.just(ResponseEntity.ok().build());
    }

    @PostMapping
    public Single<Order> create(@RequestBody Order orderRequest) {
        return Single.just(iOrderService.createOrder(orderRequest));

    }
}
