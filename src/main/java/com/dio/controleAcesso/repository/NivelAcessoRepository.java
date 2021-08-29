package com.dio.controleAcesso.repository;

import com.dio.controleAcesso.model.NivelAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {
}
