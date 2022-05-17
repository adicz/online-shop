package pl.sda.shop.onlineshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String descrpition;
    @URL
    private String photo;
    private Integer availability;
    @OneToOne
    private Category category;
    @Min(1)
    private Double price;
    private String brand;
}
