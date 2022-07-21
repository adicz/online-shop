package pl.sda.shop.onlineshop.controller.mapper;

import pl.sda.shop.onlineshop.controller.dto.order.OrderCreateDto;
import pl.sda.shop.onlineshop.controller.dto.order.OrderResponseDto;
import pl.sda.shop.onlineshop.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderMapper {

    public static Order mapToOrder(OrderCreateDto orderCreateDto) {
        return Order.builder()
                .orderDate(orderCreateDto.getOrderDate())
                .shippingMethod(orderCreateDto.getShippingMethod())
                .paymentMethod(orderCreateDto.getPaymentMethod())
                .address(orderCreateDto.getAddress())
                .shoppingCart(orderCreateDto.getShoppingCart())
                .price(orderCreateDto.getPrice())
                .build();
    }

    public static OrderResponseDto mapOrderToOrderResponseDto(Order order) {
        DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDateTime = order.getOrderDate().format(newDateFormat);
        return new OrderResponseDto(
                order.getId(),
                formatDateTime,
                order.getOrderStatus(),
                order.getShippingMethod(),
                order.getPaymentMethod(),
                order.getAddress(),
                order.getShoppingCart(),
                order.getPrice());
    }
}
