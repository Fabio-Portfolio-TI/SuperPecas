package br.com.masterclass.superpecas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Peca")
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PecaId")
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

    @Column(name = "CarroId")
    private Long carroId;

}
