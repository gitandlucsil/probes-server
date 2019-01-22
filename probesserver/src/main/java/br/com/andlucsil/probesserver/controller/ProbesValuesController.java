package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;
import br.com.andlucsil.probesserver.repository.ProbesValuesRepository;

@Controller
@RequestMapping("/probesvalues")
public class ProbesValuesController {

	@Autowired
	ProbesValuesRepository probesvaluesrepository;
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	//Retorna todas as leituras
	@GetMapping("/")
	public String lista(Model model){
		//${reads} será a variável disponível no template thymeleaf
		model.addAttribute("reads",probesvaluesrepository.findAll());
		return "probesvalues/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
	@GetMapping("/{sensor}")
	public String visualizar(@PathVariable String sensor, Model model){
		model.addAttribute("reads", probesvaluesrepository.todasLeiturasSensor(sensor));
		return "probesvalues/lista";
	}
}
