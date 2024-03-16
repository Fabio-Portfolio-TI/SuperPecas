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
@Table(name = "Carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarroID")
    private Long carroId;

    @Column(name = "nomeModelo")
    private String nomeModelo;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "CodigoUnico")
    private String codigoUnico;
}
