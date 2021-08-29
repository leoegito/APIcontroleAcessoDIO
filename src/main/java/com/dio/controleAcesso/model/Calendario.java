package com.dio.controleAcesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Calendario {
    @Id
    @GeneratedValue
    private Long idCalendario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idTipoData")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TipoData tipoData;

    private String descricao;
    private LocalDateTime dataEspecial;

    @JsonIgnore
    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL)
    private List<Movimentacao> movimentacao;
}
