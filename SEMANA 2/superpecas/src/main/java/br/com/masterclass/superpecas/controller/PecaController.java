package br.com.masterclass.superpecas.controller;


import br.com.masterclass.superpecas.model.dto.PecaDTO;
import br.com.masterclass.superpecas.service.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peca")
@Tag(name = "Peça", description = "EndPoints para controle de peças.")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping("/{id}")
    @Operation(summary = "Busque a Peça pelo seu ID",
            description ="Busque a Peça pelo seu ID",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PecaDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<PecaDTO> buscaPecaById(@PathVariable Long id) {
        PecaDTO peca = pecaService.buscaPecaById(id);
        return ResponseEntity.ok(peca);
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Listar todas as Peças",
            description ="Listar todas as Peças",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PecaDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<PecaDTO>> listarTodasPecas() {
        List<PecaDTO> pecas = pecaService.listarTodas();
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/listarTodosPaginado")
    @Operation(summary = "Listar todas as Peças Paginado",
            description ="Listar todas as Peças Paginado",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PecaDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Page<PecaDTO>> listarTodasPecasPaginado(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<PecaDTO> pecas = pecaService.listarTodasPaginado(page, size);
        return ResponseEntity.ok(pecas);
    }

    @GetMapping("/listarTodosPaginado/{termo}")
    @Operation(summary = "Listar todas as Peças Paginado com 'Termo'",
            description ="Listar todas as Peças Paginado com 'Termo'",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PecaDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Page<PecaDTO>> listarPecasPorTermoPaginado(@PathVariable String termo,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<PecaDTO> pecas = pecaService.listarTodasPaginadoTermo(termo, page, size);
        return ResponseEntity.ok(pecas);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um Peça",
            description ="Cadastrar um Peça",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<?> salvarPeca(@RequestBody PecaDTO pecaDTO) {
        pecaService.salvarPeca(pecaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Peça cadastrada com sucesso!");
    }

    @PutMapping
    @Operation(summary = "Atualizar uma Peça",
            description ="Atualizar uma Peça",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<?> updatePeca(@RequestBody PecaDTO pecaDTO) {
        pecaService.salvarPeca(pecaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Peça atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma Peça pelo ID",
            description ="Excluir uma Peça pelo ID",
            tags = {"Peça"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<String> excluirPeca(@PathVariable Long id) {
        pecaService.excluirPeca(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
    }
}