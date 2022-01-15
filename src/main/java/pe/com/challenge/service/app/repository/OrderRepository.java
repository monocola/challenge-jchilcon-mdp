package pe.com.challenge.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.challenge.service.app.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {


}
