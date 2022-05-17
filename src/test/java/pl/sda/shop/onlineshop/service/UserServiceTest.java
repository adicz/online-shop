package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.user.UserAlreadyExist;
import pl.sda.shop.onlineshop.exception.user.UserNotFoundException;
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

    private static User USER;
    private static User USER_TO_CREATE;
    private static User USER_UPDATED;

    @BeforeAll
    static void beforeAll() {
        USER = User.builder()
                .id(1L)
                .username("adicz")
                .password("1234")
                .firstname("Adrian")
                .lastname("Czyż")
                .email("adrian.czyz@xyz.pl")
                .address(new Address())
                .image(null)
                .notifyOption(NotifyOption.NONE)
                .build();

        USER_UPDATED = User.builder()
                .id(1L)
                .username("adicz")
                .password("1234")
                .firstname("Kamil")
                .lastname("Czyż")
                .email("adrian.czyz@xyz.pl")
                .address(new Address())
                .image(null)
                .notifyOption(NotifyOption.NONE)
                .build();

        USER_TO_CREATE = User.builder()
                .username("adicz")
                .password("1234")
                .email("adrian.czyz@xyz.pl")
                .build();
    }

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
                "User with id = 1 not found in database");
    }

    @Test
    void shouldAddNewUser() {
        //GIVEN
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(USER);
        //WHEN
        User result = userService.save(USER_TO_CREATE);
        //THEN
        assertEquals(USER, result);
    }

    @Test
    void shouldThrowExceptionIfUserExistInDatabase() {
        //GIVEN
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(true);
        // todo czemu test nie przechodzi jeżeli mamy użytą linijkę poniżej?
        //Mockito.when(userRepository.existsByEmail(any())).thenReturn(true);
        //WHEN & THEN
        assertThrows(UserAlreadyExist.class,
                () -> userService.save(USER),
                "User with username 'adicz' or email 'adrian.czyz@xyz.pl' already exist in database");
    }

    @Test
    void shouldUpdateUser() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(USER));
        Mockito.when(userRepository.save(any())).thenReturn(USER_UPDATED);
        //WHEN
        User result = userService.update(USER);
        //THEN
        assertEquals(USER, result);
    }

    @Test
    void shouldThrowExceptionIfUserDoesntExistWhenUpdate() {
        //GIVEN

        //WHEN & THEN
    }

}