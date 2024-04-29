package org.example.atp.config.Security.DTOS;

import java.util.stream.Collectors;

import org.example.atp.config.Security.UserEntity;
import org.example.atp.config.Security.UserRole;
import org.springframework.stereotype.Component;


@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDto.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.toSet())
                ).build();
    }

}