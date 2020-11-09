package br.com.linkstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.linkstart.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
