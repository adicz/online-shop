package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final String FIRST_PAGE = "0";
    private final String DEFAULT_PAGE_SIZE = "20";
    private final String SORT_BY_TITLE = "title";

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> paginateALlByCategory(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = FIRST_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(defaultValue = SORT_BY_TITLE) String sortBy) {
        return ResponseEntity.ok(productService.findAll(categoryName, brand, title, PageRequest.of(page, size, Sort.by(sortBy))));
    }
}
