package org.example.atp.config.Security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional <UserEntity> findByUsername(String username);
}
