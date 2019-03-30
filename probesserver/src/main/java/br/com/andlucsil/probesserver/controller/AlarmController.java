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
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.Alarm;
import br.com.andlucsil.probesserver.model.ProbeDescription;
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
	
	@PutMapping("probedesc/{probedescid}/alarm/{alarmid}")
	public @Valid Alarm updateAlarm(@PathVariable (value = "probedescid") Long desc_id, @PathVariable (value = "alarmid") Long alarm_id, @Valid @RequestBody Alarm alarm) {
		if(!probedescriptionrepository.existsById(desc_id)) {
			throw new ResourceNotFoundException("ProbeDescriptionId " + desc_id + " não encontrado");
		} else {
			alarm.setProbedescription(probedescriptionrepository.findById(desc_id).orElse(new ProbeDescription()));
		}
		return alarmrepository.findById(alarm_id).map(alr -> {
			alr.setActive(alarm.isActive());
			alr.setDescription(alarm.getDescription());
			alr.setValue(alarm.getValue());
			alr.setType(alarm.isType());
			alr.setProbedescription(alarm.getProbedescription());
			return alarmrepository.save(alr);
		}).orElseThrow(() -> new ResourceNotFoundException("AlarmId " + alarm_id + " não encontrado"));
	}
	
	@DeleteMapping("/alarm/{id}")
	public ResponseEntity<?> deleteAlarm(@PathVariable Long id){
		return alarmrepository.findById(id).map(alarm -> {
			alarmrepository.delete(alarm);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("AlarmId " + id + " não encontrado"));
	}
	
	@GetMapping("/alarm/actives")
	public List<Alarm> getAllAlarmsActives(){
		return alarmrepository.findAllActives();
	}
	
	@GetMapping("probedesc/{probedescid}/alarm/actives")
	public List<Alarm> getAllAlarmsActivesByProbes(@PathVariable (value = "probedescid") Long id){
		return alarmrepository.findAllActivesById(id);
	}
	
	@GetMapping("/alarm/isactive/{id}")
	public Boolean getIfAlarmIsActive(@PathVariable (value = "id") Long id) {
		return alarmrepository.findAlarmActive(id);
	}
}
