package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.controller.dto.product.ProductResponseDto;
import pl.sda.shop.onlineshop.controller.mapper.ProductMapper;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.service.ProductService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final String FIRST_PAGE = "0";
    private final String DEFAULT_PAGE_SIZE = "20";
    private final String SORT_BY_TITLE = "title";

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchProduct(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = FIRST_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(defaultValue = SORT_BY_TITLE) String sortBy) {
        return ResponseEntity.ok(productService.findProductsByCategoryNameAndBrandNameAndTitle(
                        categoryName,
                        brand,
                        title,
                        PageRequest.of(page, size, Sort.by(sortBy)))
                .map(ProductMapper::mapProductToProductResponseDto));
    }

    @GetMapping("/defaultImage")
    public ResponseEntity<byte[]> getProductDefaultImage() {
        byte[] defaultImage = productService.getDefaultImage();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                .body(defaultImage);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
