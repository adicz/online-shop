package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

}
