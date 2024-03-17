package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    @Query("Select DISTINCT fabricante from Carro")
    List<String> findAllFabricantes();

    @Query("SELECT c.fabricante, COUNT(c) AS total FROM Carro c GROUP BY c.fabricante ORDER BY total DESC limit 10")
    List<String> findTop10Fabricantes();

    @Query("SELECT c FROM Carro c WHERE LOWER(c.nomeModelo) LIKE LOWER(concat('%', :termo, '%')) OR LOWER(c.fabricante) LIKE LOWER(concat('%', :termo, '%'))")
    Page<Carro> findPagedByTerm(@Param("termo") String termo, Pageable pageable);

}

