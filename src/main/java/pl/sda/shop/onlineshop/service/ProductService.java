package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        if(productRepository.existsById(id)) {
            Product p = productRepository.getById(id);
            p.setTitle(title);
            productRepository.save(p);
        }
        throw new NoSuchElementException(String.format("Product with id = %d not found in database", id));
    }

    public void updatePrice(Long id, Double price) {
        if(productRepository.existsById(id)) {
            Product p = productRepository.getById(id);
            p.setPrice(price);
            productRepository.save(p);
    }
        throw new NoSuchElementException(String.format("Product with id = %d not found in database", id));
    }

    public void updateDescription(Long id, String description) {
        if(productRepository.existsById(id)) {
            Product p = productRepository.getById(id);
            p.setDescrpition(description);
            productRepository.save(p);
    }
        throw new NoSuchElementException(String.format("Product with id = %d not found in database", id));
    }

    public void updatePhoto(Long id, String url) {
        if(productRepository.existsById(id)) {
            Product p = productRepository.getById(id);
            p.setPhoto(url);
            productRepository.save(p);
        }
        throw new NoSuchElementException(String.format("Product with id = %d not found in database", id));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
