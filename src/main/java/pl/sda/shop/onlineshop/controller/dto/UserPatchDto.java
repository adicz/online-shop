package pl.sda.shop.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPatchDto {

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private Address address;
    @NotNull
    private NotifyOption notifyOption;

}
