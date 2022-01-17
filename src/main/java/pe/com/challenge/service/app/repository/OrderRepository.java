package pe.com.challenge.service.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.challenge.service.app.entity.Order;

import java.util.UUID;

/**
 * Interface repositorio que define los métodos necesario para mapear ordenes <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {




}
