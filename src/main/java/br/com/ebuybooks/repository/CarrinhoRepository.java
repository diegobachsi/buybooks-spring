package br.com.ebuybooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ebuybooks.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	@Query("select c from Carrinho c join c.user u where u.username = :username")
	List<Carrinho> findAllByUser(@Param("username")String username);

	@Query("select c from Carrinho c join c.user u where u.username = :username and c.tituloLivro = :titulo")
	Carrinho findAllByBook(@Param("username")String username, @Param("titulo")String titulo);
	
	@Query("select c from Carrinho c where c.id = :id")
	Carrinho findCarrinhoById(@Param("id")Long id);
	
}
