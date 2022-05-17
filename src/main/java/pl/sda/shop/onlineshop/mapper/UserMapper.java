package pl.sda.shop.onlineshop.mapper;

import pl.sda.shop.onlineshop.dto.UserCreateDto;
import pl.sda.shop.onlineshop.model.User;

public class UserMapper {

    public static User mapToUser(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .email(userCreateDto.getEmail())
                .build();
    }
}
