package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.model.dto.PecaDTO;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PecaDTO buscaPecaById(Long id) {
        Peca peca = pecaRepository.findById(id).orElse(null);
        return modelMapper.map(peca, PecaDTO.class);
    }

    public List<PecaDTO> listarTodas() {
        List<Peca> pecas = pecaRepository.findAll();
        return pecas.stream()
                .map(peca -> modelMapper.map(peca, PecaDTO.class))
                .collect(Collectors.toList());
    }

    public Page<PecaDTO> listarTodasPaginado(int page, int size) {
        Page<Peca> pecaPage = pecaRepository.findAll(PageRequest.of(page, size));
        return pecaPage.map(peca -> modelMapper.map(peca, PecaDTO.class));
    }

    public Page<PecaDTO> listarTodasPaginadoTermo(String termo, int page, int size) {
        Page<Peca> pecaPage = pecaRepository.findPagedByTerm(termo, PageRequest.of(page, size));
        return pecaPage.map(peca -> modelMapper.map(peca, PecaDTO.class));
    }

    public ResponseEntity<?> salvarPeca(PecaDTO pecaDTO) {
        try {
            Optional<Carro> carroOptional = carroRepository.findById(pecaDTO.getCarroID());
            if (carroOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
            }

            Carro carro = carroOptional.get();
            Peca peca = modelMapper.map(pecaDTO, Peca.class);
            peca.setCarro(carro);

            if (peca.getPecaId() != null) {
                Optional<Peca> existingPecaOptional = pecaRepository.findById(peca.getPecaId());
                if (existingPecaOptional.isPresent()) {
                    Peca existingPeca = existingPecaOptional.get();
                    modelMapper.map(pecaDTO, existingPeca);
                    pecaRepository.save(existingPeca);
                    return ResponseEntity.status(HttpStatus.OK).build();
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } else {
                pecaRepository.save(peca);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno");
        }
    }

    public ResponseEntity<?> excluirPeca(Long id){
        if(pecaRepository.existsById(id)){
            pecaRepository.deleteAllById(Collections.singleton(id));
            return ResponseEntity.status(HttpStatus.OK).body("Peça excluída com sucesso...");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Peça não encontrada");
        }
    }
}
