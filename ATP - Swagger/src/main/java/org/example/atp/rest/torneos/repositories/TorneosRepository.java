package org.example.atp.rest.torneos.repositories;

import org.example.atp.rest.torneos.models.Torneos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneosRepository extends JpaRepository<Torneos, Long> {
}
