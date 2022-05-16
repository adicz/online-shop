package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exceptions.user.UserNotFoundException;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id = %d not fount in database", id)));
    }

    User save(User user) {
        return null;
    }

    User update(User user) {
        return null;
    }

    boolean delete(User user) {
        return false;
    }


}
