package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/name")
    public ResponseEntity<Category> findByName(@RequestParam String categoryName) {
        return ResponseEntity.ok(categoryService.findByName(categoryName));
    }

    @GetMapping("/parent")
    public ResponseEntity<List<Category>> findByParent(@RequestParam Category parent) {
        return ResponseEntity.ok(categoryService.findByParent(parent));
    }

    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

}
