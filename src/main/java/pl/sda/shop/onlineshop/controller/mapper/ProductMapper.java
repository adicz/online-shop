package pl.sda.shop.onlineshop.controller.mapper;

import pl.sda.shop.onlineshop.controller.dto.product.ProductResponseDto;
import pl.sda.shop.onlineshop.model.Product;

public class ProductMapper {

    private static final String DEFAULT_PRODUCT_IMAGE_PATH = "http://localhost:8080/product/defaultImage";
    private static final String PRODUCT_IMAGE_PATH = "http://localhost:8080/product/%d/image";

    public static ProductResponseDto mapProductToProductResponseDto(Product product) {
        String productImagePath = DEFAULT_PRODUCT_IMAGE_PATH;
        if (product.getImage() != null) {
            productImagePath = String.format(PRODUCT_IMAGE_PATH, product.getId());
        }
        return new ProductResponseDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                productImagePath,
                product.getAvailability(),
                product.getCategory().getName(),
                product.getPrice(),
                product.getBrand(),
                product.getVersion());
    }
}
