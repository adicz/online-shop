package pl.sda.shop.onlineshop.controller.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.model.enumerated.OrderStatus;
import pl.sda.shop.onlineshop.model.enumerated.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private String orderDate;
    private OrderStatus orderStatus;
    private ShippingMethod shippingMethod;
    private PaymentMethod paymentMethod;
    private Address address;
    private ShoppingCart shoppingCart;
    private BigDecimal price;
}
