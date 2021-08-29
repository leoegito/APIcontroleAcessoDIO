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
public class Ocorrencia{
    @Id
    @GeneratedValue
    private Long idOcorrencia;
    private String nome;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL)
    private List<Movimentacao> movimentacao;
}
