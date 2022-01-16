package pe.com.challenge.service.app.expose.web;

import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.dto.order.OrderDTO;
import pe.com.challenge.service.app.dto.orderdetail.OrderDetailDTO;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.entity.OrderDetail;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orderdetail")
@CrossOrigin(origins = "http://localhost:4200")
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
        iOrderService.updateOrderDetail(orderDetail);
    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<OrderDetailDTO>> getOrderById(@PathVariable UUID id) {
        return Maybe.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(iOrderService.getOrderDetailById(id)));
    }


}
