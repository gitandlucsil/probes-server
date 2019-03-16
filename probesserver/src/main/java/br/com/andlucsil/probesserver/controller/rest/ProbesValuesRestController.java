package br.com.andlucsil.probesserver.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.ProbesIdf;
import br.com.andlucsil.probesserver.model.ProbesValues;
import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;
import br.com.andlucsil.probesserver.repository.ProbesValuesRepository;

@RestController
@RequestMapping("/rest/probesvalues")
public class ProbesValuesRestController {

	@Autowired
	ProbesValuesRepository probesvaluesrepository;
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	@GetMapping("/")
	public List<ProbesValues> lista(Model model){
		return probesvaluesrepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ProbesValues visualizar(@PathVariable(value = "id") Long id) {
		return probesvaluesrepository.findById(id).orElse(new ProbesValues());
	}
}
