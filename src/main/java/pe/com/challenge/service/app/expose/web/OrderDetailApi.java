package pe.com.challenge.service.app.expose.web;

import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.dto.orderdetail.OrderDetailDTO;
import pe.com.challenge.service.app.entity.OrderDetail;
import pe.com.challenge.service.app.exeption.BeanNotFoundException;
import pe.com.challenge.service.app.util.Constant;

import java.util.UUID;

/**
 * clase controller que define los métodos necesario para listar detalle de ordenes <br/>
 * <b>Interface</b>: IOrderService <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/orderdetail")

public class OrderDetailApi {

    @Autowired
    public IOrderService iOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<OrderDetail> addProduct(@RequestBody OrderDetail orderRequest) {
        return Single.just(iOrderService.addProductToOrderDetail(orderRequest));

    }

    @DeleteMapping("/{orderid}")
    public Single<ResponseEntity<Void>> deleteProductInOrder(@PathVariable UUID orderid){
        if(!iOrderService.getOrderDetailByID(orderid).isPresent()) {
            return Single.just(ResponseEntity.notFound().build());
        }
        iOrderService.deleteProductByOrderDetail(orderid);
        return Single.just(ResponseEntity.ok().build());
    }

    @PutMapping
    public void updateStateCompleteOrderById(@RequestBody OrderDetail orderDetail) {
        try {
            iOrderService.updateOrderDetail(orderDetail);
        } catch (Exception e){
            throw new BeanNotFoundException(Constant.MESSAGE_NO_CONTENT);
        }

    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<OrderDetailDTO>> getOrderById(@PathVariable UUID id) {
        return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iOrderService.getOrderDetailById(id)));
    }


}
