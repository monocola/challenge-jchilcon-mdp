package pe.com.challenge.service.app.expose.web;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.swagger.client.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.dto.order.OrderDTO;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.exeption.BeanNotFoundException;
import pe.com.challenge.service.app.util.Constant;
import java.util.UUID;

/**
 * clase controller que define los métodos necesario para listar ordenes <br/>
 * <b>Interface</b>: iOrderService <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */

@RestController
@RequestMapping(value = "/api/v1/order")
@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<OrderDTO>> getOrderById(@PathVariable UUID id) {
         return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iOrderService.getOrderById(id)));
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<Order> create(@RequestBody Order orderRequest) {
        return Single.just(iOrderService.createOrder(orderRequest));

    }

    @PutMapping("/completada/{id}")
    public void updateStateCompleteOrderById(@PathVariable UUID id) {
        try{
            iOrderService.updateStateCompleteOrderById(id);
        }catch (Exception e){
            throw new BeanNotFoundException(Constant.MESSAGE_NO_FOUND);
        }


    }

    @PutMapping("/rechazada/{id}")
    public void updateStateRejectOrderById(@PathVariable UUID id) {
        try{
            iOrderService.updateStateRejectOrderById(id);
        }catch (Exception e){
            throw new BeanNotFoundException(Constant.MESSAGE_NO_FOUND);
        }


    }
}
