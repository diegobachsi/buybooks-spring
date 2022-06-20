package br.com.ebuybooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ebuybooks.model.Venda;

@Repository
public interface VendasRepository extends JpaRepository<Venda, Long> {
	
	@Query("select v from Venda v join v.user u where u.username = :username")
	List<Venda> findAllByUser(@Param("username")String username);
	
}
