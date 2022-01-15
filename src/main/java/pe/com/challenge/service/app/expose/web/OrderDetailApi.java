package pe.com.challenge.service.app.expose.web;

import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.entity.OrderDetail;

@RestController
@RequestMapping(value = "/api/v1/orderdetail")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailApi {

    @Autowired
    public IOrderService iOrderService;

    @PostMapping
    public Single<OrderDetail> create(@RequestBody OrderDetail orderRequest) {
        return Single.just(iOrderService.createOrderDetail(orderRequest));

    }



}
