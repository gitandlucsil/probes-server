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

import br.com.andlucsil.probesserver.model.ProbeValue;
import br.com.andlucsil.probesserver.repository.ProbeValueRepository;

@RestController
@RequestMapping("/rest/probevalue")
public class ProbeValueController {

	@Autowired
	ProbeValueRepository probevaluerepository;
	
	@GetMapping("/")
	public List<ProbeValue> lista(Model model){
		return probevaluerepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ProbeValue visualizar(@PathVariable Long id) {
		return probevaluerepository.findById(id).orElse(new ProbeValue());
	}
	
	@PostMapping("/")
	public void salvar(@RequestBody ProbeValue probevalue){
		probevaluerepository.save(probevalue);
	}
	
	@PutMapping("/")
	public void atualizar(@RequestBody ProbeValue probevalue) {
		probevaluerepository.save(probevalue);
	}
	
}
