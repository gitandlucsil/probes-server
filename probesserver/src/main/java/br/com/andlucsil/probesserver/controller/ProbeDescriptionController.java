package br.com.andlucsil.probesserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.ProbeDescription;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;

@RestController
@RequestMapping("/probe")
public class ProbeDescriptionController {

	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;
	
	/*Retorna a informação de todos os sensores*/
	@GetMapping("/")
	public List<ProbeDescription> getAll(){
		return probedescriptionrepository.findAll();
	}
	/*Retorna um sensor pelo seu id*/
	@GetMapping("/{id}")
	public ProbeDescription getProbeDescription(@PathVariable Long id) {
		return probedescriptionrepository.findById(id).orElse(new ProbeDescription());
	}
	/*Salva um sensor no banco*/
	@PostMapping("/")
	public ProbeDescription savePostDescription(@Valid @RequestBody ProbeDescription probedescription){
		return probedescriptionrepository.save(probedescription);
	}
	/*Altera as informacoes de um determinado sensor*/
	@PutMapping("/{id}")
	public @Valid ProbeDescription updatePostDescription(@PathVariable Long id, @Valid @RequestBody ProbeDescription probedescription){
		return probedescriptionrepository.findById(id).map(probedesc -> {
			probedesc.setId(probedescription.getId());
			probedesc.setDescription(probedescription.getDescription());
			return probedescriptionrepository.save(probedesc);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeDescriptionId " + id + " não encontrado"));
	}
	/*Exclui um determinado sensor*/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProbeDescription(@PathVariable Long id){
		return probedescriptionrepository.findById(id).map(probedesc -> {
			probedescriptionrepository.delete(probedesc);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeDescriptionId " + id + " não encontrado"));
	}
	
}