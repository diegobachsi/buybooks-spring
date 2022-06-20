package br.com.ebuybooks.controller;

import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ebuybooks.dto.RequisicaoMontarCarrinho;
import br.com.ebuybooks.model.Carrinho;
import br.com.ebuybooks.model.Livro;
import br.com.ebuybooks.model.User;
import br.com.ebuybooks.repository.CarrinhoRepository;
import br.com.ebuybooks.repository.LivrosRepository;
import br.com.ebuybooks.repository.UserRepository;


@Controller
@RequestMapping(value = { "/" , "" ,"/home" })
public class HomeController {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public String home(Model model) {
		
		List<Livro> livros = livrosRepository.findAll();
		model.addAttribute("livros", livros);
		return "home";
	}
	
	@PostMapping
	public String current(RequisicaoMontarCarrinho requisicao, Model model) {

		String titulo = requisicao.getTitulo();
		BigDecimal valor = requisicao.getValor();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User usuario = userRepository.findByUsername(username);
		Carrinho carrinho = requisicao.toCarrinho();

		Carrinho livro = carrinhoRepository.findAllByBook(username, titulo);

		if (livro == null){
			carrinho.setUser(usuario);
			carrinhoRepository.save(carrinho);
			model.addAttribute("mensagem", "Livro " + carrinho.getTituloLivro() + " adicionado com sucesso ao seu carrinho.");
		}else{
			livro.setQuantidade(livro.getQuantidade() + 1);
			livro.setValorTotal(livro.getValorTotal().add(valor));
			carrinhoRepository.save(livro);
			model.addAttribute("mensagem", "Livro " + livro.getTituloLivro() + " adicionado com sucesso ao seu carrinho.");
		}

		List<Livro> livros = livrosRepository.findAll();
		model.addAttribute("livros", livros);

		return "home";
	}
	
}
