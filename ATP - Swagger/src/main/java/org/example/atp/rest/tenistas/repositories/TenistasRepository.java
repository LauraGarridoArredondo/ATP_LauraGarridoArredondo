package org.example.atp.rest.tenistas.repositories;

import org.example.atp.rest.tenistas.models.Tenistas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenistasRepository extends JpaRepository<Tenistas, Long> {
}
