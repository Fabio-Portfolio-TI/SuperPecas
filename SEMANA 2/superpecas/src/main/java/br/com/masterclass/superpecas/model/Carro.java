package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "carros")
@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarroID")
    private Long carroId;

    @Column(name = "NomeModelo")
    private String nomeModelo;

    @Column(name = "Fabricante")
    private String fabricante;

    @Column(name = "CodigoUnico")
    private String codigoUnico;


}
