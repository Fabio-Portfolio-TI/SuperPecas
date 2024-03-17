package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.PecaDTO;
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

//    @GetMapping("/listarTodosPaginado/{termo}")
//    public ResponseEntity<Page<PecaDTO>> listarPecasPorTermoPaginado(@PathVariable String termo,
//                                                                     @RequestParam(defaultValue = "0") int page,
//                                                                     @RequestParam(defaultValue = "10") int size) {
//        Page<PecaDTO> pecas = pecaService.listarTodasPaginadoTermo(termo, page, size);
//        return ResponseEntity.ok(pecas);
//    }

    @PostMapping
    public ResponseEntity<PecaDTO> salvarPeca(@RequestBody PecaDTO pecaDTO) {
        PecaDTO savedPeca = pecaService.salvarPeca(pecaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPeca);
    }

//    @PutMapping
//    public ResponseEntity<PecaDTO> atualizarPeca(@RequestBody PecaDTO pecaDTO) {
//        PecaDTO updatedPeca = pecaService.atualizarPeca(pecaDTO);
//        return ResponseEntity.ok(updatedPeca);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPeca(@PathVariable Long id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.noContent().build();
    }
}