package pe.com.challenge.service.app.repository;

import io.swagger.client.model.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.challenge.service.app.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {


}
