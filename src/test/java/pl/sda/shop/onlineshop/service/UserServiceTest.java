package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shop.onlineshop.exception.user.UserAlreadyExists;
import pl.sda.shop.onlineshop.exception.user.UserNotFoundException;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;
import pl.sda.shop.onlineshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    private static List<User> USER_LIST;

    @BeforeAll
    void beforeAll() {
        USER = createUser("Adrian");
        USER_TO_CREATE = createUser("Kamil");

        USER_TO_CREATE = new User();
        USER.setUsername("adicz");
        USER.setPassword("1234");
        USER.setEmail("adrian.czyz@xyz.pl");

        USER_LIST = List.of(USER, USER, USER);
    }

    private User createUser(String firstname) {
        User user = new User();
        user.setId(1L);
        user.setUsername("adicz");
        user.setPassword("1234");
        user.setFirstname(firstname);
        user.setLastname("Czyż");
        user.setEmail("adrian.czyz@xyz.pl");
        user.setAddress(new Address());
        user.setImage(null);
        user.setNotifyOption(NotifyOption.NONE);
        return user;
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
    void shouldReturnAllUsers() {
        //GIVEN
        Mockito.when(userRepository.findAll()).thenReturn(USER_LIST);
        //WHEN
        List<User> users = userService.findAll();
        //THEN
        assertEquals(USER_LIST, users);
    }

    @Test
    void shouldAddNewUser() {
        //GIVEN
        Mockito.when(userRepository.existsByUsernameAndEmail(any(), any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(USER);
        //WHEN
        User result = userService.save(USER_TO_CREATE);
        //THEN
        assertEquals(USER, result);
    }

    @Test
    void shouldThrowExceptionIfUserExistInDatabase() {
        //GIVEN
        Mockito.when(userRepository.existsByUsernameAndEmail(any(), any())).thenReturn(true);
        //WHEN & THEN
        assertThrows(UserAlreadyExists.class,
                () -> userService.save(USER),
                "User with username 'adicz' or email 'adrian.czyz@xyz.pl' already exist in database");
    }

    @Test
    void shouldRemoveUser() {
        //WHEN
        boolean result = userService.deleteById(USER_ID);
        //THEN
        assertTrue(result);
    }

}