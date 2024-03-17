package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "carros")
@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarroID", nullable = false)
    private Long carroId;

    @Column(name = "NomeModelo", nullable = false)
    private String nomeModelo;

    @Column(name = "Fabricante", nullable = false)
    private String fabricante;

    @Column(name = "CodigoUnico", nullable = false)
    private String codigoUnico;


}
