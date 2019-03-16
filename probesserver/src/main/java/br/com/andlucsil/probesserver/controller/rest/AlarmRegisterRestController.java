package br.com.andlucsil.probesserver.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.AlarmRegister;
import br.com.andlucsil.probesserver.model.Alarms;
import br.com.andlucsil.probesserver.repository.AlarmRegisterRepository;

@RestController
@RequestMapping("/rest/alarmregister")
public class AlarmRegisterRestController {

	@Autowired
	AlarmRegisterRepository alarmRegisterRepository;
	
	
	@GetMapping("/")
	public List<AlarmRegister> lista(Model model){
		return alarmRegisterRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public AlarmRegister visualizar(@PathVariable(value = "id") Long id) {
		return alarmRegisterRepository.findById(id).orElse(new AlarmRegister());
	}
}
