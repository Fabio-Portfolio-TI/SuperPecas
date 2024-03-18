package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.Peca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PecaRepository extends JpaRepository<Peca, Long> {
    Collection<Object> findByCarro(Carro carro);

@Query("SELECT p FROM Peca p WHERE LOWER(p.nome) LIKE LOWER(concat('%', :termo, '%')) OR LOWER(p.numeroSerie) LIKE LOWER(concat('%', :termo, '%')) OR LOWER(p.modeloCarro) LIKE LOWER(concat('%', :termo, '%')) OR LOWER(p.fabricante) LIKE LOWER(concat('%', :termo, '%'))")
    Page<Peca> findPagedByTerm(@Param("termo") String termo, Pageable pageable);

}

