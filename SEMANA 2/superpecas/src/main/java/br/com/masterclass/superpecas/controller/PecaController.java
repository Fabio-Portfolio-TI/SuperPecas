package br.com.masterclass.superpecas.controller;


import br.com.masterclass.superpecas.entity.Peca;
import br.com.masterclass.superpecas.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {
    @Autowired
    private PecaService pecaService;

    @GetMapping
    public List<Peca> listarPecas() {
        return pecaService.listarPecas();
    }

    @GetMapping("/{id}")
    public Peca buscarPecaPorId(@PathVariable Long id) {
        return pecaService.buscarPecaPorId(id);
    }

    @PostMapping
    public Peca cadastrarPeca(@RequestBody PecaDTO pecaDTO) {
        return pecaService.cadastrarPeca(pecaDTO);
    }

    @PostMapping("/{id}")
    public Peca atualizarPeca(@PathVariable Long id, @RequestBody PecaDTO pecaDTO) {
        return pecaService.atualizarPeca(id, pecaDTO);
    }

    @DeleteMapping("/{id}")
    public void excluirPeca(@PathVariable Long id) {
        pecaService.excluirPeca(id);
    }
}
