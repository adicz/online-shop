package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.dto.UserCreateDto;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.service.UserService;

import static pl.sda.shop.onlineshop.mapper.UserMapper.mapToUser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.save(mapToUser(userCreateDto)));
    }

    private User userMapper(UserCreateDto user) {
        User userToCreate = new User();
        userToCreate.setUsername(user.getUsername());
        userToCreate.setPassword(user.getPassword());
        userToCreate.setEmail(user.getEmail());
        return userToCreate;
    }

}
