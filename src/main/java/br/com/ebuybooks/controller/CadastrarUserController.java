package br.com.ebuybooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ebuybooks.dto.RequisicaoCadastrarUser;
import br.com.ebuybooks.model.User;
import br.com.ebuybooks.repository.UserRepository;


@Controller
@RequestMapping("/cadastro")
public class CadastrarUserController {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	@GetMapping
	public String cadastrarUser() {

		return "cadastro";
	}
	
	@PostMapping
	public String novo(RequisicaoCadastrarUser requisicao, Model model) {
		
		String username = requisicao.getUsername();
		String senha = requisicao.getPassword();
		String repeatSenha = requisicao.getRepeatPassword();
		
		User pesquisarUsername = usuarioRepository.findByUsername(username);
		
		if (pesquisarUsername == null) {
			
			if (senha.equals(repeatSenha)) {
				
				User user = requisicao.toUser();
				
				user.setEnabled(true);
				
				usuarioRepository.save(user);
				
				model.addAttribute("ok", username);
				
			} else {
				
				model.addAttribute("erro", "Senhas divergentes.");
				
			}
			
		} else {
			
			model.addAttribute("erro", "Usuário já está cadastrado.");
			
		}
		
		

		return "cadastro";
	}
	
}