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
public class TipoData {
    @Id
    @GeneratedValue
    private long idTipoData;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoData", cascade = CascadeType.ALL)
    private List<Calendario> calendario;
}
