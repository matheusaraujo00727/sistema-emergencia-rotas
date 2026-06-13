package com.example.backend.repository;

import com.example.backend.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    Optional<Recurso> findByPlaca(String placa);

}
