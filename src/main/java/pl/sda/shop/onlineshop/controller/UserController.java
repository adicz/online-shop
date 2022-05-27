package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.controller.dto.UserCreateDto;
import pl.sda.shop.onlineshop.controller.dto.UserResponseDto;
import pl.sda.shop.onlineshop.controller.mapper.UserMapper;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static pl.sda.shop.onlineshop.controller.mapper.UserMapper.mapToUser;
import static pl.sda.shop.onlineshop.controller.mapper.UserMapper.mapUserToUserResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(mapUserToUserResponseDto(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll().stream()
                .map(UserMapper::mapUserToUserResponseDto)
                .toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patchUser(@RequestBody User user) {
        return ResponseEntity.ok(mapUserToUserResponseDto(userService.update(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

}
