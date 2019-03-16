package br.com.andlucsil.probesserver.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.ProbesIdf;
import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;

@RestController
@RequestMapping("/rest/probesidfs")
public class ProbesIdfRestController {

	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	@GetMapping("/")
	public List<ProbesIdf> lista(Model model){
		return probesidfrepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ProbesIdf visualizar(@PathVariable Long id) {
		return probesidfrepository.findById(id).orElse(new ProbesIdf());
	}
}
