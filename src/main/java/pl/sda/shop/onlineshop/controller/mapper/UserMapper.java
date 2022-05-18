package pl.sda.shop.onlineshop.controller.mapper;

import pl.sda.shop.onlineshop.controller.dto.UserCreateDto;
import pl.sda.shop.onlineshop.controller.dto.UserResponseDto;
import pl.sda.shop.onlineshop.model.User;

import java.util.List;

public class UserMapper {

    public static User mapToUser(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .email(userCreateDto.getEmail())
                .build();
    }

    public static List<UserResponseDto> mapUserToUserResponseDto(List<User> users) {
        return users.stream().map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getAddress(),
                        user.getImage(),
                        user.getNotifyOption()))
                .toList();
    }

    public static UserResponseDto mapUserToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getAddress(),
                user.getImage(),
                user.getNotifyOption());
    }
}
