package pe.com.challenge.service.app.bussines.impl;

import io.swagger.client.model.OrderResponse;
import io.swagger.client.model.OrderResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DisposableSqlTypeValue;
import org.springframework.stereotype.Service;
import pe.com.challenge.service.app.bussines.IOrderService;
import pe.com.challenge.service.app.dto.order.OrderDTO;
import pe.com.challenge.service.app.dto.orderdetail.OrderDetailDTO;
import pe.com.challenge.service.app.entity.Order;
import pe.com.challenge.service.app.entity.OrderDetail;
import pe.com.challenge.service.app.entity.Product;
import pe.com.challenge.service.app.repository.OrderDetailsRepository;
import pe.com.challenge.service.app.repository.OrderRepository;
import pe.com.challenge.service.app.repository.ProductRepository;
import pe.com.challenge.service.app.util.Constant;
import pe.com.challenge.service.app.bussines.rulers.Tax;

import java.sql.Timestamp;
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
    public ProductRepository productRepository;

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
                Product product = productRepository.findProductEntityById(ord.getProductId());
                preSubtotal.updateAndGet(v -> new Float((float) (v + ord.getTotalAmount())));
                io.swagger.client.model.OrderDetail orderDetail = new io.swagger.client.model.OrderDetail();
                orderDetail.setOrderDetailId(ord.getId().toString());
                orderDetail.setProductId(ord.getProductId().toString());
                orderDetail.setProductName(product.getName());
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
    public OrderDTO getOrderById(UUID orderDetailId) {
        OrderDTO orderDTO = new OrderDTO();

        List<OrderResponseBody>  responseBodyList = new ArrayList<>();
        Optional<Order> order = orderRepository.findById(orderDetailId);
        AtomicReference<Float> preSubtotal = new AtomicReference<>(Constant.FLOAT_CERO);
        OrderResponseBody orderResponseBody = new OrderResponseBody();
        orderDTO.setId(order.get().getId().toString());
        orderDTO.setOrderNumber(order.get().getOrderNumber());
        orderDTO.setCustomer(order.get().getCustomer());
        orderDTO.setState(order.get().getState());
        orderDTO.setCreatedAt(order.get().getCreatedAt().toString());
        List<OrderDetail> orderDetails = orderDetailsRepository.findOrderDetailById(order.get().getId());
        List<io.swagger.client.model.OrderDetail> orderDetailSingle = new ArrayList<>();

        orderDetails.forEach(ord ->{
            Product productE = productRepository.findProductEntityById(ord.getProductId());
            preSubtotal.updateAndGet(v -> new Float((float) (v + ord.getTotalAmount())));
            io.swagger.client.model.OrderDetail orderDetail = new io.swagger.client.model.OrderDetail();
            orderDetail.setOrderDetailId(ord.getId().toString());
            orderDetail.setProductId(ord.getProductId().toString());
            orderDetail.setQuantity(String.valueOf((ord.getQuantity())));
            orderDetail.setUnityPrice(String.valueOf(ord.getUnityPrice()));
            orderDetail.setProductName(productE.getName());
            orderDetail.setTotalAmount(String.valueOf(ord.getTotalAmount()));
            orderDetail.setCreatedAt(ord.getCreatedAt().toString());
            orderDTO.setSubTotal(preSubtotal.toString());
            orderDetailSingle.add(orderDetail);
            orderResponseBody.setOrderDetail(orderDetailSingle);
        });
        String subTotal = preSubtotal.toString();
        orderDTO.setOrderDetail(orderDetailSingle);
        //taxes:

        orderDTO.setSubTotal(subTotal);
        orderDTO.setTotalCityTax(Tax.getTotalCityTax(subTotal));

        orderDTO.setTotalCountTax(Tax.getTotalCountTax(subTotal, orderDTO.getTotalCityTax()) );
        orderDTO.setTotalStateTax(Tax.getTotalStateTax(subTotal, orderDTO.getTotalCityTax(),
                orderDTO.getTotalCountTax()));
        orderDTO.setTotalFederalTax(Tax.getTotalFederalTax(subTotal, orderDTO.getTotalCityTax(),
                orderDTO.getTotalCountTax(), orderDTO.getTotalStateTax()));
        orderDTO.setTotalTax(Tax.getTotalTax(orderDTO.getTotalCityTax(), orderDTO.getTotalCountTax(),
                orderDTO.getTotalStateTax(), orderDTO.getTotalFederalTax()));
        orderDTO.setTotal(Tax.getTotal(subTotal, orderDTO.getTotalTax()));

        responseBodyList.add(orderResponseBody);

        return orderDTO;
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
    public OrderDetail addProductToOrderDetail(OrderDetail orderDetail) {
        Product product = productRepository.findProductEntityById(orderDetail.getProductId());
        float totalAmount = orderDetail.getQuantity() * product.getUnityPrice();
        orderDetail.setUnityPrice(product.getUnityPrice());
        orderDetail.setTotalAmount(totalAmount);
        return orderDetailsRepository.save(orderDetail);

    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
     OrderDetail orderDetailT=  orderDetailsRepository.findOrderDetailEntityById(orderDetail.getId());
     Product product = productRepository.findProductEntityById(orderDetailT.getProductId());
     float totalAmount = orderDetail.getQuantity() * product.getUnityPrice();
     orderDetailT.setQuantity(orderDetail.getQuantity());
     orderDetailT.setUnityPrice(product.getUnityPrice());
     orderDetailT.setTotalAmount(totalAmount);
     Long datetime = System.currentTimeMillis();
     Timestamp date = new Timestamp(datetime);
     orderDetailT.setCreatedAt(date);
     orderDetailsRepository.save(orderDetailT);

    }

    @Override
    public OrderDetailDTO getOrderDetailById(UUID orderDetailId) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        OrderDetail orderDetailT=  orderDetailsRepository.findOrderDetailEntityById(orderDetailId);
        Product product = productRepository.findProductEntityById(orderDetailT.getProductId());
        orderDetailDTO.setOrderDetailId(orderDetailT.getOrderId().toString());
        orderDetailDTO.setProductId(orderDetailT.getProductId().toString());
        orderDetailDTO.setProductName(product.getName());
        orderDetailDTO.setQuantity(orderDetailT.getQuantity().toString());
        orderDetailDTO.setUnityPrice(String.valueOf(orderDetailT.getUnityPrice()));
        orderDetailDTO.setTotalAmount(String.valueOf(orderDetailT.getTotalAmount()));
        orderDetailDTO.setCreatedAt(orderDetailT.getCreatedAt().toString());
        return orderDetailDTO;
    }


}
