package pe.com.challenge.service.app.bussines.impl;

import io.swagger.client.model.OrderResponse;
import io.swagger.client.model.OrderResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.entity.OrderDetail;
import pe.com.challenge.service.app.repository.OrderDetailsRepository;
import pe.com.challenge.service.app.repository.OrderRepository;
import pe.com.challenge.service.app.util.Constant;
import pe.com.challenge.service.app.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * clase implementadora que define los métodos necesario para listar ordenes <br/>
 * <b>Interface</b>: IOrderService <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderResponse getOrders() {
        OrderResponse orderResponse = new OrderResponse();
        List<OrderResponseBody>  responseBodyList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        orderList.forEach(or ->{
            AtomicReference<Float> preSubtotal = new AtomicReference<>(Constant.FLOAT_CERO);
            AtomicReference<Float> total = new AtomicReference<>(Constant.FLOAT_CERO);
            OrderResponseBody orderResponseBody = new OrderResponseBody();
            orderResponseBody.setId(or.getId().toString());
            orderResponseBody.setOrderNumber(or.getOrderNumber());
            orderResponseBody.setCustomer(or.getCustomer());



            orderResponseBody.setState(1);
            orderResponseBody.setCreatedAt(or.getCreatedAt().toString());
            List<OrderDetail> orderDetails = orderDetailsRepository.findOrderDetailById(or.getId());
            List<io.swagger.client.model.OrderDetail> orderDetails1 = new ArrayList<>();

            orderDetails.forEach(ord ->{
                preSubtotal.updateAndGet(v -> new Float((float) (v + ord.getTotalAmount())));
                io.swagger.client.model.OrderDetail orderDetail = new io.swagger.client.model.OrderDetail();
                orderDetail.setOrderDetailId(ord.getId().toString());
                orderDetail.setProductId(ord.getProductId().toString());
                //orderDetail.setProductName(ord.getProductName());
                orderDetail.setQuantity(String.valueOf((ord.getQuantity())));
                orderDetail.setUnityPrice(String.valueOf(ord.getUnityPrice()));
                orderDetail.setTotalAmount(String.valueOf(ord.getTotalAmount()));
                orderDetail.setCreatedAt(ord.getCreatedAt().toString());

                orderDetails1.add(orderDetail);
                orderResponseBody.setOrderDetail(orderDetails1);
            });
            String subTotal = preSubtotal.toString();

            //taxes:
            orderResponseBody.setSubTotal(subTotal);
            orderResponseBody.setTotalCityTax(Utils.getTotalCityTax(subTotal));
            orderResponseBody.setTotalCountTax(Utils.getTotalCountTax(subTotal, orderResponseBody.getTotalCityTax()) );
            orderResponseBody.setTotalStateTax(Utils.getTotalStateTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax()));
            orderResponseBody.setTotalFederalTax(Utils.getTotalFederalTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax(), orderResponseBody.getTotalStateTax()));
            orderResponseBody.setTotalTax(Utils.getTotalTax(orderResponseBody.getTotalCityTax(), orderResponseBody.getTotalCountTax(),
                    orderResponseBody.getTotalStateTax(), orderResponseBody.getTotalFederalTax()));
            orderResponseBody.setTotal(Utils.getTotal(subTotal, orderResponseBody.getTotalTax()));

            responseBodyList.add(orderResponseBody);
        });

        orderResponse.setOrders(responseBodyList);
        return orderResponse;
    }

    @Override
    public Optional<OrderDetail> getOrderDetailByID(UUID orderDetailId) {
        return orderDetailsRepository.findById(orderDetailId);
    }

    @Override
    public void deleteProduct(UUID orderDetailId) {
        orderDetailsRepository.deleteById(orderDetailId);
    }

    @Override
    public Order createOrder(Order orderEntity) {
        orderEntity.setOrderNumber(Utils.generateOrderNumber());
        orderEntity.setQuantityTax(0);
        orderEntity.setTotalTax(0);
        orderEntity.setQuantityTotal(0);
        orderEntity.setUnityPrice(0);
        orderEntity.setState(1);
        orderEntity.setCustomer("JORGE CHILCON");
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
       return orderDetailsRepository.save(orderDetail);

    }


}
