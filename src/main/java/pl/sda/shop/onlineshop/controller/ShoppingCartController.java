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
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.service.ShoppingCartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<ShoppingCart> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<ShoppingCart> addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return ResponseEntity.ok(shoppingCartService.save(shoppingCart));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.deleteById(id));
    }

}
