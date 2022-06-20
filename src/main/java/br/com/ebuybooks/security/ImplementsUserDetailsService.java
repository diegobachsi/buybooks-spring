package br.com.ebuybooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.ebuybooks.model.User;
import br.com.ebuybooks.repository.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = usuarioRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não existe!");
		}
		
		return user;
	}

}
