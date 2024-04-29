package org.example.atp.config.Security;

import lombok.RequiredArgsConstructor;
import org.example.atp.config.Security.DTOS.CreateUserDto;
import org.example.atp.config.Security.errors.exceptions.NewUserWithDifferentPasswordsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserEnitityService extends ServicioBase<UserEntity, Long, UserEntityRepository> {
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> findByUsername(String username) {
        return this.repositorio.findByUsername(username);
    }

    public UserEntity nuevoUsuario(CreateUserDto newUser) {

        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder().username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword())).avatar(newUser.getAvatar())
                    .roles(Stream.of(UserRole.USER).collect(Collectors.toSet())).build();
            try {
                return save(userEntity);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
            }
        } else {
            throw new NewUserWithDifferentPasswordsException();
        }

    }
}
