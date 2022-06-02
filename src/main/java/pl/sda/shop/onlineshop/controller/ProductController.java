package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.service.CategoryService;
import pl.sda.shop.onlineshop.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestBody Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@RequestBody Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

        @GetMapping("/category")
        ResponseEntity<List<Product>> getProductByCategory(@RequestBody Category category) {
            return ResponseEntity.ok(productService.findByCategory(category));
        }

        @GetMapping("/parent")
        ResponseEntity<List<Product>> getProductByParent(@RequestBody Category category) {
        List<Category> categories = categoryService.findByParent(category);
            return ResponseEntity.ok(productService.findByParent(categories));
        }

    @GetMapping("/brand")
    ResponseEntity<List<Product>> getProductByBrand(@RequestBody String brand) {
        return ResponseEntity.ok(productService.findByBrand(brand));
    }

    @GetMapping("/title")
    ResponseEntity<List<Product>> getProductByTitle(@RequestBody String title) {
        return ResponseEntity.ok(productService.findByTitle(title));
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Product>> paginateALlByCategory(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(productService.findAll(categoryName, PageRequest.of(page, size, Sort.unsorted())));
    }
}
