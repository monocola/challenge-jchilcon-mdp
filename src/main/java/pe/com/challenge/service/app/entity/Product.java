package pe.com.challenge.service.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Clase que define los atributos de productos <br/>
 * <b>Class</b>: Product <br/>
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 13, 2022.
 */

@Entity
@Table(name="products")
@Data
public class Product implements java.io.Serializable{

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

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("unityPrice")
    @Column(name= "unity_price")
    private float unityPrice;

    @JsonProperty("state")
    private Integer state;

    @JsonProperty("createdAt")
    @Column(name = "created_at")
    @Generated(GenerationTime.INSERT)
    private Timestamp createdAt;


}
