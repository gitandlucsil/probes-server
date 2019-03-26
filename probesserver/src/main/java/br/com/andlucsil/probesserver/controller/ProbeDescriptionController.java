package br.com.andlucsil.probesserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.ProbeDescription;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;

@RestController
@RequestMapping("/rest/probedesc")
public class ProbeDescriptionController {

	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;
	
	@GetMapping("/")
	public List<ProbeDescription> lista(Model model){
		return probedescriptionrepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ProbeDescription visualizar(@PathVariable Long id) {
		return probedescriptionrepository.findById(id).orElse(new ProbeDescription());
	}
	
	@PostMapping("/")
	public void salvar(@RequestBody ProbeDescription probedescription){
		probedescriptionrepository.save(probedescription);
	}
	
	@PutMapping("/")
	public void atualizar(@RequestBody ProbeDescription probedescription){
		probedescriptionrepository.save(probedescription);
	}
	
	
}