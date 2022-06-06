package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.category.CategoryAlreadyExists;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.repository.AddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        if (addressRepository.existsAddressByUser(address.getUser())) {
            throw new CategoryAlreadyExists(String.format(
                    "Address with with user ID '%s' already exist in database", address.getUser()));
        }
        return addressRepository.save(address);
    }

    public Address findByUser(User user) throws ClassNotFoundException {
        return addressRepository.findAddressByUser(user).orElseThrow(
                () -> new ClassNotFoundException(String.format("Address not found in database")));
    }

    public Address update(Address address) {
        addressRepository.findById(address.getId());
        return addressRepository.save(address);
    }

    public boolean deleteById(Long id) {
        addressRepository.deleteById(id);
        return true;
    }
}
