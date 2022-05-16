package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exceptions.user.UserNotFoundException;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;
import pl.sda.shop.onlineshop.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    //todo różnica pomiędzy @Mock a @MockBean
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final long USER_ID = 1L;

    private final User USER = new User(
            1L,
            "adicz",
            "1234",
            "Adrian",
            "Czyż",
            "adrian.czyz@wp.pl",
            new Address(),
            null,
            NotifyOption.NONE);

    @Test
    void shouldReturnUserById() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(USER));
        //WHEN
        User result = userService.findById(USER_ID);
        //THEN
        assertEquals(USER, result);
    }

    @Test
    void shouldThrowExceptionIfUserDoesntExist() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        //WHEN & THEN
        assertThrows(UserNotFoundException.class,
                () -> userService.findById(USER_ID),
                "User with id = 1 not fount in database");
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }
}