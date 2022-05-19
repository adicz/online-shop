package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.math.BigDecimal;
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

    public List<Product> findAll() {return productRepository.findAll();}

    public List<Product> findByCategory(Category category){
        return productRepository.findByCategory(category);
    }

    public List<Product> findByBrand(String brand){
        return productRepository.findByBrand(brand);
    }

    public List<Product> findByTitle(String title){
        return productRepository.findByTitle(title);
    }

    public void updateTitle(Long id, String title) {
        Product product = findById(id);
        product.setTitle(title);
        productRepository.save(product);
    }

    public void updatePrice(Long id, BigDecimal price) {
        Product product = findById(id);
        product.setPrice(price);
        productRepository.save(product);
    }

    public void updateDescription(Long id, String description) {
        Product product = findById(id);
        product.setDescrpition(description);
        productRepository.save(product);
    }

    public void updatePhoto(Long id, String url) {
        Product product = findById(id);
        product.setPhoto(url);
        productRepository.save(product);
    }

    public boolean deleteById(Long id) {
        productRepository.deleteById(id);
        return true;
    }

}
