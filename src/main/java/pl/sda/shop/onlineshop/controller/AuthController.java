package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.shop.onlineshop.controller.dto.UserCreateDto;
import pl.sda.shop.onlineshop.controller.dto.UserResponseDto;
import pl.sda.shop.onlineshop.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

import static pl.sda.shop.onlineshop.controller.mapper.UserMapper.mapToUser;
import static pl.sda.shop.onlineshop.controller.mapper.UserMapper.mapUserToUserResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapUserToUserResponseDto(userService.save(mapToUser(userCreateDto))));
    }

    @GetMapping("/current")
    public ResponseEntity<UserResponseDto> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(mapUserToUserResponseDto(userService.findByUsername(principal.getName())));
    }


}
