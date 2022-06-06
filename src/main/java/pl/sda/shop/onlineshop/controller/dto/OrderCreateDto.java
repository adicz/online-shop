package pl.sda.shop.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.PaymentMethod;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private ShippingMethod shippingMethod;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Address address;

    @NotNull
    private ShoppingCart shoppingCart;


}
