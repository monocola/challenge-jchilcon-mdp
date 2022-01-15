package pe.com.challenge.service.app.bussines;


import io.reactivex.Completable;
import io.swagger.client.model.OrderResponse;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.entity.OrderDetail;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface service que define los métodos necesario para listar ordenes <br/>
 * <b>Class</b>: OrderServiceImpl <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */


public interface IOrderService {

    OrderResponse getOrders();

    Optional<OrderDetail> getOrderDetailByID(UUID orderDetailId);

    public void deleteProduct(UUID orderDetailId);

    public Order createOrder(Order order);

    public OrderDetail createOrderDetail(OrderDetail orderDetail);

}
