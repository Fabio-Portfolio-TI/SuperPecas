package br.com.masterclass.superpecas.controller;


import br.com.masterclass.superpecas.model.dto.PecaDTO;
import br.com.masterclass.superpecas.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peca")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping("/{id}")
    public ResponseEntity<PecaDTO> buscaPecaById(@PathVariable Long id) {
        PecaDTO peca = pecaService.buscaPecaById(id);
        return ResponseEntity.ok(peca);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PecaDTO>> listarTodasPecas() {
        List<PecaDTO> pecas = pecaService.listarTodas();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/listarTodosPaginado")
    public ResponseEntity<Page<PecaDTO>> listarTodasPecasPaginado(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<PecaDTO> pecas = pecaService.listarTodasPaginado(page, size);
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/listarTodosPaginado/{termo}")
    public ResponseEntity<Page<PecaDTO>> listarPecasPorTermoPaginado(@PathVariable String termo,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<PecaDTO> pecas = pecaService.listarTodasPaginadoTermo(termo, page, size);
        return ResponseEntity.ok(pecas);
    }

    @PostMapping
    public ResponseEntity<?> salvarPeca(@RequestBody PecaDTO pecaDTO) {
        pecaService.salvarPeca(pecaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Peça cadastrada com sucesso!");
    }

    @PutMapping
    public ResponseEntity<?> updatePeca(@RequestBody PecaDTO pecaDTO) {
        pecaService.salvarPeca(pecaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Peça atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPeca(@PathVariable Long id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
    }
}