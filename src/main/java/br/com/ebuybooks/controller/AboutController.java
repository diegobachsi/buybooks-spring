package br.com.ebuybooks.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	
	@GetMapping("/about")
	public String home(Model model) {
		return "about";
	}

}