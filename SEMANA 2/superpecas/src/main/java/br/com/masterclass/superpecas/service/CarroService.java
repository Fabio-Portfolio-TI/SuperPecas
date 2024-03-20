package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.dto.CarroDTO;
import br.com.masterclass.superpecas.projections.CarroProjection;
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

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CarroProjection getCarroProjectionById(Long id) {
        return carroRepository.findProjectionById(id);
    }

    public List<CarroProjection> listarTodos() {
        return carroRepository.findAllProjectedBy();
    }

    public Page<CarroProjection> listarTodosPaginado(int page, int size) {
        Page<CarroProjection> carroPage = carroRepository.findAllProjectedBy(PageRequest.of(page, size));
        return carroPage;
    }

    public Page<CarroProjection> findAllPagedByTerm(String termo, int numPage) {
        Pageable pageable = PageRequest.of(numPage, 10);
            Page<CarroProjection> carroPage = carroRepository.findAllPagedByTerm(termo, pageable);
            return carroPage.map(carro -> modelMapper.map(carro,CarroProjection.class));
    }

    public List<String> listarTodosFabricantes() {
        return carroRepository.findAllFabricantes();
    }

    public List<String> listarTop10Fabricantes() {
        return carroRepository.findTop10Fabricantes();
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
