package com.dio.controleAcesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class CategoriaUsuario {
    @Id
    @GeneratedValue
    private Long idCategoriaUsuario;
    private String descricao;

//    @JsonIgnore
    @OneToMany(mappedBy = "categoriaUsuario", cascade = CascadeType.ALL)
    private List<Usuario> usuario;

}
