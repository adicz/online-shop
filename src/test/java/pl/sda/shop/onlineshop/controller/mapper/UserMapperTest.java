package pl.sda.shop.onlineshop.controller.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.sda.shop.onlineshop.controller.dto.user.UserResponseDto;
import pl.sda.shop.onlineshop.model.Role;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserMapperTest {

    private static User USER_WITH_IMAGE;
    private static User USER_WITHOUT_IMAGE;
    private static Role ROLE_USER = new Role(1L, "USER");
    private static Role ROLE_ADMIN = new Role(1L, "ADMIN");

    @BeforeAll
    static void beforeAll() {
        USER_WITH_IMAGE = createUser("test", new byte[1]);
        USER_WITHOUT_IMAGE = createUser("test", null);
    }

    private static User createUser(String username, byte[] image) {
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword("12345");
        user.setEmail(username + "@gmail.com");
        user.setImage(image);
        user.setNotifyOption(NotifyOption.NONE);
        user.setRoles(List.of(ROLE_USER, ROLE_ADMIN));
        return user;
    }

    @Test
    void shouldMapUserToUserResponseDtoWithDefaultImage() {
        //GIVEN
        UserResponseDto userResponseDto = new UserResponseDto(
                1L,
                "test",
                null,
                null,
                "test@gmail.com",
                null,
                "http://localhost:8080/user/defaultImage",
                NotifyOption.NONE,
                "USER, ADMIN"
        );
        //WHEN
        UserResponseDto result = UserMapper.mapUserToUserResponseDto(USER_WITHOUT_IMAGE);
        //THEN
        assertEquals(userResponseDto, result);
    }

    @Test
    void shouldMapUserToUserResponseDtoWithCustomImage() {
        //GIVEN
        UserResponseDto userResponseDto = new UserResponseDto(
                1L,
                "test",
                null,
                null,
                "test@gmail.com",
                null,
                "http://localhost:8080/user/1/image",
                NotifyOption.NONE,
                "USER, ADMIN"
        );
        //WHEN
        UserResponseDto result = UserMapper.mapUserToUserResponseDto(USER_WITH_IMAGE);
        //THEN
        assertEquals(userResponseDto, result);
    }


}