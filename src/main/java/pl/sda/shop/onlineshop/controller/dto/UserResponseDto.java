package pl.sda.shop.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

@AllArgsConstructor
public class UserResponseDto {

    public Long id;
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public Address address;
    public String image;
    public NotifyOption notifyOption;

}
