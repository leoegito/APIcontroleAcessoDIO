package com.dio.controleAcesso.repository;

import com.dio.controleAcesso.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
