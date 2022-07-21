package pl.sda.shop.onlineshop.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private String image;
    private NotifyOption notifyOption;
    private String roles;

}
