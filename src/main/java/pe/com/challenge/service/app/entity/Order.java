package pe.com.challenge.service.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que define los atributos de la orden <br/>
 * <b>Class</b>: Order <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */

@Entity
@Table(name="orders")
@Data
public class Order implements java.io.Serializable{

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

    @JsonProperty("orderNumber")
    public String orderNumber;

    @JsonProperty("customer")
    public String customer;

    @JsonProperty("quantityTax")
    public int quantityTax;

    @JsonProperty("totalTax")
    public int totalTax;

    @JsonProperty("quantityTotal")
    public int quantityTotal;

    @JsonProperty("unityPrice")
    public int unityPrice;

    @JsonProperty("state")
    public Integer state;

    @JsonProperty("createdAt")
    @Column(name = "created_at")
    @Generated(GenerationTime.INSERT)
    private Timestamp createdAt;
}
