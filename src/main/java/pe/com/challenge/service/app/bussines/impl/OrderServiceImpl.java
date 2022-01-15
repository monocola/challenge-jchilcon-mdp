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
import pe.com.challenge.service.app.bussines.rulers.Tax;

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
            OrderResponseBody orderResponseBody = new OrderResponseBody();
            orderResponseBody.setId(or.getId().toString());
            orderResponseBody.setOrderNumber(or.getOrderNumber());
            orderResponseBody.setCustomer(or.getCustomer());
            orderResponseBody.setState(or.getState());
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
            orderResponseBody.setTotalCityTax(Tax.getTotalCityTax(subTotal));
            orderResponseBody.setTotalCountTax(Tax.getTotalCountTax(subTotal, orderResponseBody.getTotalCityTax()) );
            orderResponseBody.setTotalStateTax(Tax.getTotalStateTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax()));
            orderResponseBody.setTotalFederalTax(Tax.getTotalFederalTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax(), orderResponseBody.getTotalStateTax()));
            orderResponseBody.setTotalTax(Tax.getTotalTax(orderResponseBody.getTotalCityTax(), orderResponseBody.getTotalCountTax(),
                    orderResponseBody.getTotalStateTax(), orderResponseBody.getTotalFederalTax()));
            orderResponseBody.setTotal(Tax.getTotal(subTotal, orderResponseBody.getTotalTax()));

            responseBodyList.add(orderResponseBody);
        });

        orderResponse.setOrders(responseBodyList);
        return orderResponse;
    }

    @Override
    public OrderResponse getOrderById(UUID orderDetailId) {
        OrderResponse orderResponse = new OrderResponse();
        List<OrderResponseBody>  responseBodyList = new ArrayList<>();
        Optional<Order> order = orderRepository.findById(orderDetailId);
            AtomicReference<Float> preSubtotal = new AtomicReference<>(Constant.FLOAT_CERO);
            OrderResponseBody orderResponseBody = new OrderResponseBody();
            orderResponseBody.setId(order.get().getId().toString());
            orderResponseBody.setOrderNumber(order.get().getOrderNumber());
            orderResponseBody.setCustomer(order.get().getCustomer());
            orderResponseBody.setState(order.get().getState());
            orderResponseBody.setCreatedAt(order.get().getCreatedAt().toString());
            List<OrderDetail> orderDetails = orderDetailsRepository.findOrderDetailById(order.get().getId());
            List<io.swagger.client.model.OrderDetail> orderDetailSingle = new ArrayList<>();

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

                orderDetailSingle.add(orderDetail);
                orderResponseBody.setOrderDetail(orderDetailSingle);
            });
            String subTotal = preSubtotal.toString();

            //taxes:
            orderResponseBody.setSubTotal(subTotal);
            orderResponseBody.setTotalCityTax(Tax.getTotalCityTax(subTotal));
            orderResponseBody.setTotalCountTax(Tax.getTotalCountTax(subTotal, orderResponseBody.getTotalCityTax()) );
            orderResponseBody.setTotalStateTax(Tax.getTotalStateTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax()));
            orderResponseBody.setTotalFederalTax(Tax.getTotalFederalTax(subTotal, orderResponseBody.getTotalCityTax(),
                    orderResponseBody.getTotalCountTax(), orderResponseBody.getTotalStateTax()));
            orderResponseBody.setTotalTax(Tax.getTotalTax(orderResponseBody.getTotalCityTax(), orderResponseBody.getTotalCountTax(),
                    orderResponseBody.getTotalStateTax(), orderResponseBody.getTotalFederalTax()));
            orderResponseBody.setTotal(Tax.getTotal(subTotal, orderResponseBody.getTotalTax()));

            responseBodyList.add(orderResponseBody);


        orderResponse.setOrders(responseBodyList);
        return orderResponse;
    }

    @Override
    public Optional<OrderDetail> getOrderDetailByID(UUID orderDetailId) {
        return orderDetailsRepository.findById(orderDetailId);
    }

    @Override
    public void deleteProductByOrderDetail(UUID orderDetailId) {
        orderDetailsRepository.deleteById(orderDetailId);
    }

    @Override
    public void updateStateCompleteOrderById(UUID orderId) {
        Order order = orderRepository.getOne(orderId);
        order.setState(Constant.INTEGER_ESTADO_COMPLETADO);
        orderRepository.save(order);
    }

    @Override
    public void updateStateRejectOrderById(UUID orderId) {
        Order order = orderRepository.getOne(orderId);
        order.setState(Constant.INTEGER_ESTADO_RECHAZADO);
        orderRepository.save(order);
    }

    @Override
    public Order createOrder(Order orderEntity) {
        orderEntity.setOrderNumber(Tax.generateOrderNumber());
        orderEntity.setQuantityTax(Constant.INTEGER_CERO);
        orderEntity.setTotalTax(Constant.INTEGER_CERO);
        orderEntity.setQuantityTotal(Constant.INTEGER_CERO);
        orderEntity.setUnityPrice(Constant.INTEGER_CERO);
        orderEntity.setState(Constant.INTEGER_ESTADO_PENDIENTE);
        orderEntity.setCustomer("JORGE CHILCON");
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
       float totalAmount = orderDetail.getQuantity() * orderDetail.getUnityPrice();
        orderDetail.setTotalAmount(totalAmount);
       return orderDetailsRepository.save(orderDetail);

    }


}
