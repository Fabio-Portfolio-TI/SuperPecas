package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
