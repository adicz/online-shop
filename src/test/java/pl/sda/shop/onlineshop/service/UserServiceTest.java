package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final long USER_ID = 1L;

    private static User USER_1;
    private static User USER_1_UPDATED;
    private static User USER_2;
    private static User USER_3;
    private static User USER_TO_SAVE_IN_DATABASE;
    private static User USER_RESPONSE_FROM_DATABASE;
    private static List<User> USER_LIST;

    @BeforeAll
    static void beforeAll() {
        USER_1 = createUser(1L, "Adrian", "adicz");
        USER_1_UPDATED = createUser(1L, "Kamil", "adicz");
        USER_2 = createUser(2L, "Adam", "adamB");
        USER_3 = createUser(3L, "Michał", "misha");
        USER_TO_SAVE_IN_DATABASE = createUser("adicz");
        USER_RESPONSE_FROM_DATABASE = createUser(USER_ID, "adicz");
        USER_LIST = List.of(USER_1, USER_2, USER_3);
    }

    private static User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("12345");
        user.setEmail(username + "@gmail.com");
        return user;
    }

    private static User createUser(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword("12345");
        user.setEmail(username + "@gmail.com");
        return user;
    }

    private static User createUser(Long id, String firstname, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword("12345");
        user.setFirstname(firstname);
        user.setLastname("Kowalski");
        user.setEmail(username + "@gmail.com");
        user.setAddress(new Address());
        user.setImage(null);
        user.setNotifyOption(NotifyOption.NONE);
        return user;
    }

    @Test
    void shouldReturnUserById() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(USER_1));
        //WHEN
        User result = userService.findById(USER_ID);
        //THEN
        assertEquals(USER_1, result);
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
        Mockito.when(userRepository.save(any())).thenReturn(USER_RESPONSE_FROM_DATABASE);
        //WHEN
        User result = userService.save(USER_TO_SAVE_IN_DATABASE);
        //THEN
        assertEquals(USER_RESPONSE_FROM_DATABASE, result);
    }

    @Test
    void shouldThrowExceptionIfUserExistInDatabase() {
        //GIVEN
        Mockito.when(userRepository.existsByUsernameAndEmail(any(), any())).thenReturn(true);
        //WHEN & THEN
        assertThrows(UserAlreadyExists.class,
                () -> userService.save(USER_TO_SAVE_IN_DATABASE),
                "User with username 'adicz' or email 'adicz@gmail.com' already exist in database");
    }

    @Test
    void shouldUpdateUser() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(USER_1_UPDATED));
        Mockito.when(userRepository.save(any())).thenReturn(USER_1_UPDATED);
        //WHEN
        User result = userService.update(USER_1_UPDATED);
        //THEN
        assertEquals(USER_1_UPDATED, result);
    }

    @Test
    void shouldThrowExceptionWhenTryUpdateUserThatDoesntExists() {
        //GIVEN
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        //WHEN & THEN
        assertThrows(UserNotFoundException.class,
                () -> userService.update(USER_1_UPDATED),
                "User with id = 1 not found in database");
    }

    @Test
    void shouldRemoveUser() {
        //WHEN
        boolean result = userService.deleteById(USER_ID);
        //THEN
        assertTrue(result);
    }

}