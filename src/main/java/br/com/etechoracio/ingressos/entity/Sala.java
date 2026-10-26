package br.com.etechoracio.ingressos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_SALA")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SALA")
    private Long id;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "NR_PRECO")
    private Double preco;

    @Column(name = "DT_EXCLUSAO")
    private LocalDateTime dataExclusao;
}