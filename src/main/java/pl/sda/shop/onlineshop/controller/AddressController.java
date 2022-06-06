package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.service.AddressService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> save(@Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestBody Long id) {
        addressService.deleteById(id);
    }

    @GetMapping("/user")
    ResponseEntity<Address> getAddressByUser(@RequestBody User user) throws ClassNotFoundException {
        return ResponseEntity.ok(addressService.findByUser(user));
    }
}
