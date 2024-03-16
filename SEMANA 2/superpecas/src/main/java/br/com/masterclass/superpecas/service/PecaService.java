package br.com.masterclass.superpecas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PecaService {
    @Autowired
    private PecaRepository pecaRepository;

    public List<Peca> listarPecas() {
        return pecaRepository.findAll();
    }

    public Peca buscarPecaPorId(Long id) {
        return pecaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Peça não encontrada"));
    }

    public Peca cadastrarPeca(PecaDTO pecaDTO) {
        Peca peca = new Peca();
        peca.setNome(pecaDTO.getNome());
        peca.setDescricao(pecaDTO.getDescricao());
        peca.setNumeroSerie(pecaDTO.getNumeroSerie());
        peca.setFabricante(pecaDTO.getFabricante());
        peca.setModeloCarro(pecaDTO.getModeloCarro());
        return pecaRepository.save(peca);
    }

    public Peca atualizarPeca(Long id, PecaDTO pecaDTO) {
        Peca peca = buscarPecaPorId(id);
        peca.setNome(pecaDTO.getNome());
        peca.setDescricao(pecaDTO.getDescricao());
        peca.setNumeroSerie(pecaDTO.getNumeroSerie());
        peca.setFabricante(pecaDTO.getFabricante());
        peca.setModeloCarro(pecaDTO.getModeloCarro());
        return pecaRepository.save(peca);
    }

    ppublic void excluirPeca(Long id) {
        Peca peca = buscarPecaPorId(id);
        List<Carro> carrosAssociados = carroRepository.findByModelo(peca.getModeloCarro());
        if (!carrosAssociados.isEmpty()) {
            throw new RuntimeException("Essa peça não pode ser excluída pois está associada a carros");
        }
        pecaRepository.delete(peca);
    }
}
