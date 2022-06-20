package br.com.ebuybooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ebuybooks.dto.RequisicaoCadastrarLivro;
import br.com.ebuybooks.model.Livro;
import br.com.ebuybooks.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/cadastrarLivro")
public class CadastrarLivroController {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@GetMapping
	public String cadastrarLivro() {

		return "cadastrarLivro";
	}
	
	@PostMapping
	public String novo(RequisicaoCadastrarLivro requisicao) {
		
		Livro livro = requisicao.toLivro();
		
		livrosRepository.save(livro);

		return "cadastrarLivro";
	}
	
}