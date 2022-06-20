package br.com.ebuybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ebuybooks.model.Livro;

@Repository
public interface LivrosRepository extends JpaRepository<Livro, Long> {

}