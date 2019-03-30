package br.com.andlucsil.probesserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.ProbeValue;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;
import br.com.andlucsil.probesserver.repository.ProbeValueRepository;

@RestController
public class ProbeValueController {

	@Autowired
	ProbeValueRepository probevaluerepository;
	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;
	
	@GetMapping("/probevalue")
	public List<ProbeValue> getAllProbesValue(){
		return probevaluerepository.findAll();
	}
	
	@GetMapping("/probedesc/{probedescid}/probevalue")
	public List<ProbeValue> getAllProbesValuesByDescId(@PathVariable (value = "probedescid") Long id){
		return probevaluerepository.findByProbedescriptionId(id);
	}
	
	@PostMapping("/probedesc/{probedescid}/probevalue")
	public ProbeValue saveProbeValue(@PathVariable (value = "probedescid") Long id, @Valid @RequestBody ProbeValue probevalue){
		return probedescriptionrepository.findById(id).map(probedesc -> {
			probevalue.setProbedescription(probedesc);
			return probevaluerepository.save(probevalue);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + id + " não encontrado"));
	}
	
	@PutMapping("/probedesc/{probedescid}/probevalue/{probevalueid}")
	public ProbeValue updateProbeValue(@PathVariable (value = "probedescid") Long desc_id, @PathVariable (value = "probevalueid") Long value_id, @Valid @RequestBody ProbeValue probevalue) {
		if(!probedescriptionrepository.existsById(desc_id)) {
			throw new ResourceNotFoundException("ProbeDescriptionId " + desc_id + " não encontrado");
		}
		return probevaluerepository.findById(value_id).map(pvalue -> {
			pvalue.setRead_value(probevalue.getRead_value());
			return probevaluerepository.save(pvalue);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + value_id + " não encontrado"));
	}
	
	@DeleteMapping("/probevalue/{id}")
	public ResponseEntity<?> deleteProbeValue(@PathVariable Long id){
		return probevaluerepository.findById(id).map(probevalue -> {
			probevaluerepository.delete(probevalue);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + id + " não encontrado"));
	}
	
	//Retorna o valor médio da leitura de um sensor
	@GetMapping("/probedesc/{probedescid}/avg")
	public Double viewAvgValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.averageReadProbe(id);
	}
	
	//Retorna o valor máximo da leitura de um sensor
	@GetMapping("/probedesc/{probedescid}/max")
	public Integer viewMaxValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.maxReadProbe(id);
	}
	
	//Retorna o valor minimo da leitura de um sensor
	@GetMapping("/probedesc/{probedescid}/min")
	public Integer viewMinValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.minReadProbe(id);
	}
	
	//Retorna as ultimas cinco leituras
	@GetMapping("/probedesc/{probedescid}/lastfives")
	public List<ProbeValue> getLastFiveReads(@PathVariable Long probedescid){
		Pageable lastfives = PageRequest.of(0, 5);
		return probevaluerepository.lastFiveReads(probedescid, lastfives);
	}
}
