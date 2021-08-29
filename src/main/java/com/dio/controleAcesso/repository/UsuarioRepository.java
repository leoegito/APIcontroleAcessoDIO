package com.dio.controleAcesso.repository;

import com.dio.controleAcesso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
