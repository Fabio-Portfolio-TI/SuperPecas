package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.dto.CarroDTO;
import br.com.masterclass.superpecas.model.dto.TopTenCarDTO;
import br.com.masterclass.superpecas.service.CarroService;
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
@RequestMapping("/carro")
@Tag(name = "Carro", description = "EndPoints para controle de Carros.")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/{id}")
    @Operation(summary = "Busque o Carro pelo seu ID",
            description ="Busque o Carro pelo seu ID",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<CarroDTO> getCarroById(@PathVariable Long id) {
        CarroDTO carro = carroService.getCarroById(id);
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Listar todos os Carros",
            description ="Listar todos os Carros",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<CarroDTO>> listarTodosCarros() {
        List<CarroDTO> carros = carroService.listarTodos();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosPaginado")
    @Operation(summary = "Listar todos os Carros Paginado",
            description ="Listar todos os Carros Paginado",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Page<CarroDTO>> listarTodosCarrosPaginado(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Page<CarroDTO> carros = carroService.listarTodosPaginado(page, size);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosPaginado/{termo}")
    @Operation(summary = "Listar todos os Carros Paginado com 'Termo'",
            description ="Listar todos os Carros Paginado com 'Termo'",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Page<CarroDTO>> listarCarrosPorTermoPaginado(@PathVariable String termo,
                                                                       @RequestParam(defaultValue = "0") int page){
        Page<CarroDTO> carros = carroService.listarTodosPaginadoTermo(termo, page);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosFabricantes")
    @Operation(summary = "Listar todos os Fabricantes",
            description ="Listar todos os Fabricantes",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<String>> listarTodosFabricantes() {
        List<String> fabricantes = carroService.listarTodosFabricantes();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/listaTop10Fabricantes")
    @Operation(summary = "Listar os Top 10 Fabricantes",
            description ="Listaros Top 10 Fabricantes",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<String>> listarTop10Fabricantes() {
        List<String> fabricantes = carroService.listarTop10Fabricantes();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/listarTop10CarroComMaisPecas")
    @Operation(summary = "Listar os Top 10 Carros com mais Peças",
            description ="Listar os Top 10 Carros com mais Peças",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopTenCarDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public List<TopTenCarDTO> getTop10Car() {
        return carroService.findTop10Car();
    }

    @PostMapping
    @Operation(summary = "Cadastrar um Carro",
            description ="Cadastrar um Carro",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroDTO.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO savedCarro = carroService.salvarCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarro);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar um Carro pelo ID",
            description ="Atualizar um Carro pelo ID",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<String> atualizarCarro(@RequestBody CarroDTO carroDTO) throws Exception {
        CarroDTO updatedCarro = carroService.atualizarCarro(carroDTO);
        return ResponseEntity.ok().body("Carro atualizado com sucesso, id = " + updatedCarro.getCarroId());    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um Carro pelo ID",
            description ="Excluir um Carro pelo ID",
            tags = {"Carro"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Void> excluirCarro(@PathVariable Long id) {
        carroService.excluirCarro(id);
        return ResponseEntity.noContent().build();
    }

}
