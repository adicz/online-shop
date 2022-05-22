package pl.sda.shop.onlineshop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @Id
    private Long id;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<ProductCount> productCounts;
    private BigDecimal totalPrice;

}
