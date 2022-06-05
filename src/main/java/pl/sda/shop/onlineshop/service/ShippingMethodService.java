package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.shippingMethod.ShippingMethodNotFoundException;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.repository.ShippingMethodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingMethodService {
    private final ShippingMethodRepository shippingMethodRepository;

    public ShippingMethod findById(Long id) {
        return shippingMethodRepository.findById(id).orElseThrow(
                () -> new ShippingMethodNotFoundException(String.format("Shipping method with id = %d not fund", id)));
    }

    public List<ShippingMethod> findAll() {
        return shippingMethodRepository.findAll();
    }

    public ShippingMethod save(ShippingMethod shippingMethod) {
        return shippingMethodRepository.save(shippingMethod);
    }

    public boolean deleteById(Long id) {
        shippingMethodRepository.deleteById(id);
        return true;
    }
}
