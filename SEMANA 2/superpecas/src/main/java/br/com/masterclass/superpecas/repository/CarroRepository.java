package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.projections.CarroProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query("SELECT c.carroId AS carroId, c.nomeModelo AS nomeModelo, c.fabricante AS fabricante, c.codigoUnico AS codigoUnico FROM Carro c WHERE c.carroId = :carroId")
    CarroProjection findProjectionById(@Param("carroId") Long id);

    List<CarroProjection> findAllProjectedBy();

    Page<CarroProjection> findAllProjectedBy(Pageable pageable);

    @Query("SELECT c FROM Carro c WHERE LOWER(c.nomeModelo) LIKE LOWER(concat('%', :termo, '%')) OR LOWER(c.fabricante) LIKE LOWER(concat('%', :termo, '%'))")
    Page<CarroProjection> findAllPagedByTerm(String termo, Pageable pageable);

    @Query("Select DISTINCT fabricante from Carro")
    List<String> findAllFabricantes();

    @Query("SELECT c.fabricante, COUNT(c) AS total FROM Carro c GROUP BY c.fabricante ORDER BY total DESC limit 10")
    List<String> findTop10Fabricantes();

}

