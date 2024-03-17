package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.Peca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    Collection<Object> findByCarro(Carro carro);

//    Page<Peca> findByFabricante(String termo, PageRequest of);
}

