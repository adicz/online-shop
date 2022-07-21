package pl.sda.shop.onlineshop.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String title;
    private String description;
    private String image;
    private Integer availability;
    private String category;
    private BigDecimal price;
    private String brand;
    private Integer version;
}
