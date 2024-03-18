package br.com.masterclass.superpecas.model.dto;

import lombok.Data;

@Data
public class CarroDTO {
    private Long carroId;
    private String NomeModelo;
    private String Fabricante;
    private String CodigoUnico;
}
