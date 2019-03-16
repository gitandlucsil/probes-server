package br.com.andlucsil.probesserver.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.Alarms;
import br.com.andlucsil.probesserver.repository.AlarmsRepository;

@RestController
@RequestMapping("/rest/alarms")
public class AlarmsRestController {

	@Autowired
	AlarmsRepository alarmRepository;
	
	@GetMapping("/")
	public List<Alarms> lista(Model model){
		return alarmRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Alarms visualizar(@PathVariable(value = "id") Long id) {
		return alarmRepository.findById(id).orElse(new Alarms());
	}
}
