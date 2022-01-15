package pe.com.challenge.service.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Clase que define los atributos del detalle de la orden <br/>
 * <b>Class</b>: OrderDetail <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 14, 2022.
 */

@Entity
@Table(name="orders_details")
@Data
public class OrderDetail implements java.io.Serializable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonProperty("productId")
    private UUID productId;

    @JsonProperty("orderId")
    private UUID orderId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("unityPrice")
    private float unityPrice;

    @JsonProperty("totalAmount")
    private float totalAmount;

    @JsonProperty("createdAt")
    @Column(name = "created_at")
    @Generated(GenerationTime.INSERT)
    private Timestamp createdAt;






}
