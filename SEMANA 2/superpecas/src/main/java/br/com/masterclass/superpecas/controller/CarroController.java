package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.entity.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> listarCarros(){
        return carroService.listarCarros();
    }
    @GetMapping("{/id}")
    public Carro buscarCarroPorId(@PathVariable Long id){
        return carroService.buscarCarroPorId(id);
    }

    @PostMapping
    public Carro cadastrarCarro(@RequestBody CarroDto carroDto){
        return carroService.cadastrarCarro(CarroDTO);
    }

    @PutMapping("/{id}")
    public Carro atualizarCarro(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        return carroService.atualizarCarro(id, carroDTO);
    }

    @DeleteMapping("/{id}")
    public void excluirCarro(@PathVariable Long id) {
        carroService.excluirCarro(id);
    }

}
