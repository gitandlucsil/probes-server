package br.com.andlucsil.probesserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.Alarm;
import br.com.andlucsil.probesserver.repository.AlarmRepository;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;

@RestController
public class AlarmController {

	@Autowired
	AlarmRepository alarmrepository;
	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;
	
	@GetMapping("/alarm")
	public List<Alarm> getAllAlarms(){
		return alarmrepository.findAll();
	}
	
	@GetMapping("/alarm/{id}")
	public Alarm getAlarm(@PathVariable (value = "id") Long id) {
		return alarmrepository.findById(id).orElse(new Alarm());
	}
	
	@GetMapping("/probedesc/{probedescid}/alarm")
	public List<Alarm> getAllAlarmsByDescId(@PathVariable (value = "probedescid") Long id){
		return alarmrepository.findByProbedescriptionId(id);
	}
	
	@PostMapping("/probedesc/{probedescid}/alarm")
	public Alarm saveAlarm(@PathVariable (value = "probedescid") Long id, @Valid @RequestBody Alarm alarm) {
		return probedescriptionrepository.findById(id).map(probedesc -> {
			alarm.setProbedescription(probedesc);
			return alarmrepository.save(alarm);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + id + " não encontrado"));
	}
	
}
