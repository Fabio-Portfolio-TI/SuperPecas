package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.CarroDTO;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public CarroDTO getCarroById(Long id) {
        Carro carro = carroRepository.findById(id).orElse(null);
        return modelMapper.map(carro, CarroDTO.class);
    }

    public List<CarroDTO> listarTodos() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream()
                .map(carro -> modelMapper.map(carro, CarroDTO.class))
                .collect(Collectors.toList());
    }

    public Page<CarroDTO> listarTodosPaginado(int page, int size) {
        Page<Carro> carroPage = carroRepository.findAll(PageRequest.of(page, size));
        return carroPage.map(carro -> modelMapper.map(carro, CarroDTO.class));
    }

    public List<String> listarTodosFabricantes() {
        return carroRepository.findAllFabricantes();
    }

    public List<String> listarTop10Fabricantes() {
        return carroRepository.findTop10Fabricantes();
    }


    public Page<CarroDTO> listarTodosPaginadoTermo(String termo, int page) {
        Page<Carro> carroPage = carroRepository.findPagedByTerm(termo, PageRequest.of(page, 10));
        return carroPage.map(carro -> modelMapper.map(carro, CarroDTO.class));
    }

    public CarroDTO salvarCarro(CarroDTO carroDTO) {
        Carro carro = modelMapper.map(carroDTO, Carro.class);
        return modelMapper.map(carroRepository.save(carro), CarroDTO.class);
    }

    public CarroDTO atualizarCarro(CarroDTO carroDTO) throws BadRequestException {
        if (carroDTO.getCarroId() != null) {
            Carro carro = carroRepository.findById(carroDTO.getCarroId()).orElse(null);
            if (carro != null) {
                modelMapper.map(carroDTO, carro);
                return modelMapper.map(carroRepository.save(carro), CarroDTO.class);
            } else {
                throw new BadRequestException("Carro não encontrado");
            }
        } else {
            throw new BadRequestException("O campo carroID deve ser informado no corpo da requisição");
        }
    }

    public void excluirCarro(Long id) {
        Carro carro = carroRepository.findById(id).orElse(null);
        if (carro != null && pecaRepository.findByCarro(carro).isEmpty()) {
            carroRepository.deleteById(id);
            ResponseEntity.status(HttpStatus.OK).body("Carro excluido com sucesso!!!");
        } else {
            throw new RuntimeException("Não é possível excluir o carro pois há peças associadas a ele.");
        }
    }

}
