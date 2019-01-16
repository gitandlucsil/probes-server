package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;

@Controller
@RequestMapping("/probesidfs")
public class ProbesIdfController {
	
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	@GetMapping("/")
	public String lista(Model model){
		//${dados} será a variável disponível no template thymeleaf
		model.addAttribute("dados",probesidfrepository.findAll());
		return "probesidf/lista"; //arquivo .html dentro da pasta resources/templates
	}
}
