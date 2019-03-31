package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.AlarmRegister;
import br.com.andlucsil.probesserver.repository.AlarmRegisterRepository;
import br.com.andlucsil.probesserver.repository.AlarmRepository;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;

import java.util.List;

@RestController
public class AlarmRegisterController {
	
	@Autowired 
	AlarmRegisterRepository alarmregisterrespository;
	@Autowired
	AlarmRepository alarmrepository;
	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;

	@GetMapping("/alarmregister")
	public List<Object[]> getAllAlarmRegister(){
		return alarmregisterrespository.allInfoRegisters();
	}
	
	@GetMapping("alarmregister/{id}")
	public List<Object[]> getAlarmRegister(@PathVariable (value = "id") Long id) {
		return alarmregisterrespository.allInfoRegisterById(id);
	}
	
	@GetMapping("/probedesc/{probedescid}/alarm/register")
	public List<Object[]> getAllAlarmRegisterByProbe(@PathVariable (value = "probedescid") Long id){
		return alarmregisterrespository.allInfoRegistersByProbe(id);
	}
	
}
