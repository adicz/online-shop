package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException
                (String.format("Product with id = %d not found in database", id)));
    }

    public List<Product> findAll(PageRequest of) {return productRepository.findAll();}

    public List<Product> findAll() {return productRepository.findAll();}

    public List<Product> findByCategory(Category category){
        return productRepository.findProductsByCategory(category);
    }

    public List<Product> findByParent(List<Category> categories){
        List<Product> products = null;
        for (Category category : categories){
            products.add((Product) productRepository.findProductsByCategory(category).stream());
        }
        return products;
    }

    public List<Product> findByBrand(String brand){
        return productRepository.findProductsByBrand(brand);
    }

    public List<Product> findByTitle(String title){
        return productRepository.findProductsByTitle(title);
    }

    public Product update(Product product) {
        productRepository.findById(product.getId()).orElseThrow(
                () -> new NoSuchElementException(String.format("Product with id = %d not found in database", product.getId())));
        findById(product.getId());
        return productRepository.save(product);
    }

    public boolean deleteById(Long id) {
        productRepository.deleteById(id);
        return true;
    }


    public Page<Product> findAll(String categoryName, Pageable pageable) {
        return productRepository.findProductsByCategoryName(categoryName, pageable);
    }
}
