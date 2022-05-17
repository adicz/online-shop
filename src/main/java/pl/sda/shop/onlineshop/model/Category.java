package pl.sda.shop.onlineshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private Long id;
    private String categoryName;
    @OneToOne
    private Category parent;


    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

}
