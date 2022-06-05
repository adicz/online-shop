package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.shop.onlineshop.controller.dto.UserPatchDto;
import pl.sda.shop.onlineshop.exception.role.RoleNotFoundException;
import pl.sda.shop.onlineshop.exception.user.ContentTypeException;
import pl.sda.shop.onlineshop.exception.user.UserAlreadyExistsException;
import pl.sda.shop.onlineshop.exception.user.UserNotFoundException;
import pl.sda.shop.onlineshop.model.Role;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.repository.RoleRepository;
import pl.sda.shop.onlineshop.repository.UserRepository;
import pl.sda.shop.onlineshop.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static pl.sda.shop.onlineshop.controller.mapper.UserMapper.updateUserFields;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageUtils imageUtils;

    private final String USER_DEFAULT_ROLE = "USER";
    private final String DEFAULT_IMAGE_LOCALIZATION = "\\src\\main\\resources\\user.png";
    private final String IMAGE_FILE_TYPE = "image/jpeg";

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new UserAlreadyExistsException(user.getUsername(), user.getEmail());
        }
        addToUserDefaultRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void addToUserDefaultRole(User user) {
        Role userRole = roleRepository.findByName(USER_DEFAULT_ROLE)
                .orElseThrow(() -> new RoleNotFoundException(USER_DEFAULT_ROLE));
        user.setRoles(List.of(userRole));
    }

    public User update(UserPatchDto userPatchDto, Long id) {
        User user = findById(id);
        User updatedUser = updateUserFields(userPatchDto, user);
        return userRepository.save(updatedUser);
    }

    public User updateUserImage(MultipartFile multipartFile, Long userId) {
        String contentType = multipartFile.getContentType();
        if(!IMAGE_FILE_TYPE.equals(contentType)) {
            throw new ContentTypeException(contentType);
        }
        User user = findById(userId);
        parseUserImage(multipartFile, user);
        return userRepository.save(user);
    }

    private void parseUserImage(MultipartFile multipartFile, User user) {
        try {
            byte[] image = multipartFile.getBytes();
            byte[] croppedImage = imageUtils.cropImageSquare(image);
            user.setImage(croppedImage);
        } catch (IOException e) {
            log.error("Couldn't parse user image", e);
        }
    }

    private byte[] parseDefaultImage(Path path) {
        byte[] defaultImage = null;
        try {
            defaultImage = Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Couldn't parse image", e);
        }
        return defaultImage;
    }

    public byte[] getDefaultImage() {
        Path path = getDefaultImagePath();
        return parseDefaultImage(path);
    }

    private Path getDefaultImagePath() {
        File file = new File("");
        String absolutePath = file.getAbsolutePath();
        Path path = Paths.get(absolutePath + DEFAULT_IMAGE_LOCALIZATION);
        return path;
    }

    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
