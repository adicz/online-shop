package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.service.ShippingMethodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shippingMethod")
public class ShippingMethodController {

    private final ShippingMethodService shippingMethodService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<ShippingMethod> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shippingMethodService.findById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<List<ShippingMethod>> findAll() {
        return ResponseEntity.ok(shippingMethodService.findAll());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<ShippingMethod> addShippingMethod(@RequestBody ShippingMethod shippingMethod) {
        return ResponseEntity.ok(shippingMethodService.save(shippingMethod));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(shippingMethodService.deleteById(id));
    }
}
