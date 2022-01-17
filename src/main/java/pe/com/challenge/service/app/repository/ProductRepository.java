package pe.com.challenge.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.challenge.service.app.entity.OrderDetail;
import pe.com.challenge.service.app.entity.Product;

import java.util.List;
import java.util.UUID;

/**
 * Interface repositorio que define los métodos necesario para mapear productos <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM public.products WHERE id = :id", nativeQuery = true)
    public Product findProductEntityById(
            @Param("id") UUID id);




}
