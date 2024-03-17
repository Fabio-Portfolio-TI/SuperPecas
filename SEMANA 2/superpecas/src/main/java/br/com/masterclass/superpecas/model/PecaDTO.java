package br.com.masterclass.superpecas.model;

import lombok.Data;

@Data
public class PecaDTO {
    private String nome;
    private String descricao;
    private String numeroSerie;
    private String fabricante;
    private String modeloCarro;
    private Long carroID;
}
