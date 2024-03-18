package br.com.masterclass.superpecas.model.dto;

import lombok.Data;

@Data
public class TopTenCarDTO {
    private Long carroId;
    private String nomeModelo;
    private String fabricante;
    private String codigoUnico;
    private long numPecasAssociadas;
}
