package org.example.atp.config.Security;

import lombok.RequiredArgsConstructor;
import org.example.atp.config.Security.DTOS.CreateUserDto;
import org.example.atp.config.Security.DTOS.GetUserDto;
import org.example.atp.config.Security.DTOS.UserDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserEnitityService userEntityService;
    private final UserDtoConverter userDtoConverter;


    @PostMapping("/")
    public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userDtoConverter.convertUserEntityToGetUserDto(userEntityService.nuevoUsuario(newUser))
        );

    }

}
