package pl.sda.shop.onlineshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private byte[] image;
    private Integer availability;
    @OneToOne
    private Category category;
    @Min(
            value = 0,
            message = "Product price can't be lower than 0")
    private BigDecimal price;
    private String brand;
    private Integer version;
}
