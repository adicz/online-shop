package pl.sda.shop.onlineshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.enumerated.DeliveryMethod;
import pl.sda.shop.onlineshop.model.enumerated.OrderStatus;
import pl.sda.shop.onlineshop.model.enumerated.PaymentMethod;

import javax.persistence.*;

@Entity(name = "`Order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne
    private Address address;

    @OneToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private User user;


}
