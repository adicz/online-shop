package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.product.ProductNotFoundException;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final String DEFAULT_IMAGE_LOCALIZATION = "\\src\\main\\resources\\product.png";

    public Product save(Product product) {
        if (productRepository.existsById(product.getId())) {
            Integer productCurrentVersion = productRepository.getById(product.getId()).getVersion();
            product.setVersion(productCurrentVersion + 1);
            return productRepository.save(product);
        }
        product.setVersion(1);
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Page<Product> findProductsByCategoryNameAndBrandNameAndTitle(String category, String brand, String title, Pageable pageable) {
        return productRepository.findProductsByCategoryNameAndBrandNameAndTitle(category, brand, title, pageable);
    }

    public byte[] getDefaultImage() {
        Path path = getDefaultImagePath();
        return parseDefaultImage(path);
    }

    private Path getDefaultImagePath() {
        File file = new File("");
        String absolutePath = file.getAbsolutePath();
        Path path = Paths.get(absolutePath + DEFAULT_IMAGE_LOCALIZATION);
        return path;
    }

    private byte[] parseDefaultImage(Path path) {
        byte[] defaultImage = null;
        try {
            defaultImage = Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Couldn't parse image", e);
        }
        return defaultImage;
    }

    public boolean deleteById(Long id) {
        productRepository.deleteById(id);
        return true;
    }

}
