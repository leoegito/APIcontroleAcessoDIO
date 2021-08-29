package com.dio.controleAcesso.repository;

import com.dio.controleAcesso.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
}
