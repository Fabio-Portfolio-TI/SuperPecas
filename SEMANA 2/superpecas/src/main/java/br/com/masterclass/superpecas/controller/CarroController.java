package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.CarroDTO;
import br.com.masterclass.superpecas.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarroById(@PathVariable Long id) {
        CarroDTO carro = carroService.getCarroById(id);
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<CarroDTO>> listarTodosCarros() {
        List<CarroDTO> carros = carroService.listarTodos();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosPaginado")
    public ResponseEntity<Page<CarroDTO>> listarTodosCarrosPaginado(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Page<CarroDTO> carros = carroService.listarTodosPaginado(page, size);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<CarroDTO>> listarCarrosPorTermoPaginado(@PathVariable String termo,
                                                                       @RequestParam(defaultValue = "0") int page){
        Page<CarroDTO> carros = carroService.listarTodosPaginadoTermo(termo, page);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/listaTodosFabricantes")
    public ResponseEntity<List<String>> listarTodosFabricantes() {
        List<String> fabricantes = carroService.listarTodosFabricantes();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/listaTop10Fabricantes")
    public ResponseEntity<List<String>> listarTop10Fabricantes() {
        List<String> fabricantes = carroService.listarTop10Fabricantes();
        return ResponseEntity.ok(fabricantes);
    }


    @PostMapping
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO savedCarro = carroService.salvarCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarro);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> atualizarCarro(@RequestBody CarroDTO carroDTO) throws Exception {
        CarroDTO updatedCarro = carroService.atualizarCarro(carroDTO);
        return ResponseEntity.ok().body("Carro atualizado com sucesso, id = " + updatedCarro.getCarroId());    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCarro(@PathVariable Long id) {
        carroService.excluirCarro(id);
        return ResponseEntity.noContent().build();
    }
}
