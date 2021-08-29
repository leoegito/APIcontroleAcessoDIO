package com.dio.controleAcesso.repository;

import com.dio.controleAcesso.model.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {
}
