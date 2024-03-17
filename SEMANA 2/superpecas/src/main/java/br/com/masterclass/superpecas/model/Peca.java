package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pecas")
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PecaID")
    private Long pecaId;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "NumeroSerie")
    private String numeroSerie;

    @Column(name = "Fabricante")
    private String fabricante;

    @Column(name = "ModeloCarro")
    private String modeloCarro;

    @JoinColumn(name = "CarroID", nullable = false)
    @OneToOne(optional = false)
    private Carro carro;

}
