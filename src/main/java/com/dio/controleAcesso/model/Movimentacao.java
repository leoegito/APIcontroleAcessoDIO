package com.dio.controleAcesso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Movimentacao {

//    @AllArgsConstructor
//    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public class MovimentacaoId implements Serializable{
        private long idMovimento;
        public long idUsuario;
    }
    @EmbeddedId
//    @GeneratedValue
    private MovimentacaoId idMovimento;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private BigDecimal periodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOcorrencia")//coluna que possui chave estrangeira
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ocorrencia ocorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCalendario")//coluna que possui chave estrangeira
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Calendario calendario;

}
