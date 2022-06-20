package br.com.ebuybooks.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ebuybooks.model.User;

public interface UsuarioRepository extends CrudRepository<User, String> {
	
	User findByUsername(String username);

}
