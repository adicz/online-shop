package pl.sda.shop.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.shop.onlineshop.controller.dto.UserResponseDto;
import pl.sda.shop.onlineshop.controller.mapper.UserMapper;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.service.UserService;

import java.util.List;

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

    @PatchMapping("/{id}/image")
    public ResponseEntity<UserResponseDto> patchUserImage(@RequestParam MultipartFile multipartImage, @PathVariable Long id) {
        return ResponseEntity.ok(mapUserToUserResponseDto(userService.updateUserImage(multipartImage, id)));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long id) {
        byte[] image = userService.findById(id).getImage();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"1.jpg\"")
                .body(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

}
