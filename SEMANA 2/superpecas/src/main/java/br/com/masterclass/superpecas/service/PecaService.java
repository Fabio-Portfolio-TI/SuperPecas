package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.model.PecaDTO;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

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

//    public Page<PecaDTO> listarTodasPaginadoTermo(String termo, int page, int size) {
//        Page<Peca> pecaPage = pecaRepository.findByFabricante(termo, PageRequest.of(page, size));
//        return pecaPage.map(peca -> modelMapper.map(peca, PecaDTO.class));
//    }

    public PecaDTO salvarPeca(PecaDTO pecaDTO) {
        Peca peca = modelMapper.map(pecaDTO, Peca.class);
        return modelMapper.map(pecaRepository.save(peca), PecaDTO.class);
    }

//    public PecaDTO atualizarPeca(PecaDTO pecaDTO) {
//        Peca peca = modelMapper.map(pecaDTO, Peca.class);
//        return modelMapper.map(pecaRepository.save(peca), PecaDTO.class);
//    }
//
    public void excluirPeca(Long id) {
        Peca peca = pecaRepository.findById(id).orElse(null);
        if (peca != null) {
            pecaRepository.delete(peca);
        } else {
            throw new RuntimeException("Peça não encontrada.");
        }
    }
}
