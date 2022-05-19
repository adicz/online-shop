package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{category}")
    ResponseEntity<List<Product>> getProductByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @GetMapping("/{brand}")
    ResponseEntity<List<Product>> getProductByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.findByBrand(brand));
    }

    @GetMapping("/{title}")
    ResponseEntity<List<Product>> getProductByTitle(@PathVariable String title) {
        return ResponseEntity.ok(productService.findByTitle(title));
    }
}
