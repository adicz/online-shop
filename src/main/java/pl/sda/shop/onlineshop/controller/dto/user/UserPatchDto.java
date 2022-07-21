package pl.sda.shop.onlineshop.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPatchDto {

    private String firstname;
    private String lastname;

    @NotBlank(message = "Name is mandatory")
    @Email(message = "Syntax error")
    private String email;

    private Address address;

    private NotifyOption notifyOption;

}
