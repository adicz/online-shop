package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.shop.onlineshop.controller.dto.OrderCreateDto;
import pl.sda.shop.onlineshop.controller.dto.OrderResponseDto;
import pl.sda.shop.onlineshop.controller.mapper.OrderMapper;
import pl.sda.shop.onlineshop.model.Order;
import pl.sda.shop.onlineshop.service.OrderService;

import java.security.Principal;
import java.util.List;

import static pl.sda.shop.onlineshop.controller.mapper.OrderMapper.mapOrderToOrderResponseDto;
import static pl.sda.shop.onlineshop.controller.mapper.OrderMapper.mapToOrder;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapOrderToOrderResponseDto(orderService.findById(id)));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll().stream()
                .map(OrderMapper::mapOrderToOrderResponseDto)
                .toList());
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MODERATOR')")
    public ResponseEntity<List<OrderResponseDto>> findAllUserOrders(Principal principal) {
        return ResponseEntity.ok(orderService.findAllByUser(principal.getName()).stream()
                .map(OrderMapper::mapOrderToOrderResponseDto)
                .toList());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ResponseEntity<List<OrderResponseDto>> findAllUserOrders(@PathVariable String username) {
        return ResponseEntity.ok(orderService.findAllByUser(username).stream()
                .map(OrderMapper::mapOrderToOrderResponseDto)
                .toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderCreateDto orderCreateDto, Principal principal) {
        return ResponseEntity.ok(mapOrderToOrderResponseDto(orderService.save(mapToOrder(orderCreateDto), principal.getName())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteById(id));
    }

}
