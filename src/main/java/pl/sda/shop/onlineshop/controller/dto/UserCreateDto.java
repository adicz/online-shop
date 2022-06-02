package pl.sda.shop.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotNull
    @Length(min = 4)
    private String username;

    @NotNull
    @Length(min = 6)
    private String password;

    @NotNull
    @Email
    private String email;

}
