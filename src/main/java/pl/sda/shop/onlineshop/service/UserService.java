package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.user.UserAlreadyExist;
import pl.sda.shop.onlineshop.exception.user.UserNotFoundException;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id = %d not found in database", id)));
    }
    //todo jak powinniśmy nazywać metody? save, create, add?
    public User save(User user) {
        if(userExistByUsernameOrEmail(user)) {
            throw new UserAlreadyExist(String.format(
                    "User with username '%s' or email '%s' already exist in database",
                    user.getUsername(),
                    user.getEmail()));
        }
        return userRepository.save(user);
    }

    private boolean userExistByUsernameOrEmail(User user) {
        return userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.email);
    }

    public User update(User user) {
        if(userRepository.existsById(user.getId())) {
           userRepository.getById(user.getId());

        }
        throw new UserNotFoundException(String.format("User with id = %d not found in database", user.getId()));
    }

    public boolean delete(User user) {
        return false;
    }


}
