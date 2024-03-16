package br.com.masterclass.superpecas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public Carro buscarCarroPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Carro não encontrado"));
    }

    public Carro cadastrarCarro(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setModelo(carroDTO.getModelo());
        carro.setFabricante(carroDTO.getFabricante());
        return carroRepository.save(carro);
    }

    public Carro atualizarCarro(Long id, CarroDTO carroDTO) {
        Carro carro = buscarCarroPorId(id);
        carro.setModelo(carroDTO.getModelo());
        carro.setFabricante(carroDTO.getFabricante());
        return carroRepository.save(carro);
    }

    public void excluirCarro(Long id) {
        List<Peca> pecasAssociadas = pecaRepository.findByModeloCarro(carro.getModelo());
        if (!pecasAssociadas.isEmpty()) {
            throw new RuntimeException("Esse carro não pode ser excluído pois tem peças associadas a ele");
        }
        carroRepository.delete(carro);
    }
}
