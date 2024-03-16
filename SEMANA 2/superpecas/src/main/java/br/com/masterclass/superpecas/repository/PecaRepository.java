package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {
}

