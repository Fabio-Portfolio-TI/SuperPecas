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

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @Column(name = "NumeroSerie", nullable = false, unique = true)
    private String numeroSerie;

    @Column(name = "Fabricante", nullable = false)
    private String fabricante;

    @Column(name = "ModeloCarro", nullable = false)
    private String modeloCarro;

    @JoinColumn(name = "CarroID", nullable = false)
    @OneToOne(optional = false)
    private Carro carro;

}
