package pe.com.challenge.service.app.bussines;


import io.reactivex.Completable;
import io.swagger.client.model.OrderResponse;
import pe.com.challenge.service.app.dto.order.OrderDTO;
import pe.com.challenge.service.app.dto.orderdetail.OrderDetailDTO;
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

    OrderDTO getOrderById(UUID orderDetailId);

    Optional<OrderDetail> getOrderDetailByID(UUID orderDetailId);

    public void deleteProductByOrderDetail(UUID orderDetailId);

    void updateStateCompleteOrderById(UUID orderId);

    void updateStateRejectOrderById(UUID orderId);

    public Order createOrder(Order order);

    public OrderDetail addProductToOrderDetail(OrderDetail orderDetail);

    void updateOrderDetail(OrderDetail orderDetail);

    public OrderDetailDTO getOrderDetailById(UUID orderDetailId);



}
