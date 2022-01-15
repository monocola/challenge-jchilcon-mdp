package pe.com.challenge.service.app.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.challenge.service.app.entity.OrderDetail;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, UUID> {

    @Query(value = "SELECT * FROM public.orders_details WHERE order_id = :id", nativeQuery = true)
    public List<OrderDetail> findOrderDetailById(
            @Param("id") UUID id);

    @Query(value = "DELETE FROM public.orders_details WHERE order_id = :orderId AND product_id = :productId", nativeQuery = true)
    void deleteProduct( @Param("orderId") UUID orderId, @Param("productId") UUID productId);


}
