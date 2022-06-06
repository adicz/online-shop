package pl.sda.shop.onlineshop.controller.mapper;

import pl.sda.shop.onlineshop.controller.dto.order.OrderCreateDto;
import pl.sda.shop.onlineshop.controller.dto.order.OrderResponseDto;
import pl.sda.shop.onlineshop.model.Order;

public class OrderMapper {

    public static Order mapToOrder(OrderCreateDto orderCreateDto) {
        return Order.builder()
                .orderDate(orderCreateDto.getOrderDate())
                .shippingMethod(orderCreateDto.getShippingMethod())
                .paymentMethod(orderCreateDto.getPaymentMethod())
                .address(orderCreateDto.getAddress())
                .shoppingCart(orderCreateDto.getShoppingCart())
                .build();
    }

    public static OrderResponseDto mapOrderToOrderResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getOrderDate(),
                order.getOrderStatus(),
                order.getShippingMethod(),
                order.getPaymentMethod(),
                order.getAddress(),
                order.getShoppingCart());
    }
}
