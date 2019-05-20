package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*Retorna todos os registros de alarmes, retornando uma lista dos objetos que formam o registro*/
	@GetMapping("/alarmregister")
	public List<Object[]> getAllAlarmRegister(){
		return alarmregisterrespository.allInfoRegisters();
	}
	/*Retorna todos os registros de alarmes, retornando uma lista dos objetos que formam o registro*/
	@GetMapping("alarmregister/{id}")
	public List<Object[]> getAlarmRegister(@PathVariable (value = "id") Long id) {
		return alarmregisterrespository.allInfoRegisterById(id);
	}
	/*Retorna todos os registros de alarmes de um determinado sensor,retornando uma lista dos objetos que formam o registro */
	@GetMapping("/probe/{probedescid}/alarm/register")
	public List<Object[]> getAllAlarmRegisterByProbe(@PathVariable (value = "probedescid") Long id){
		return alarmregisterrespository.allInfoRegistersByProbe(id);
	}
	
	/*Retorna uma pagina de registro de alarmes*/
	@GetMapping("/alarmregister/page")
	public Page<Object[]> getPage(@RequestParam int page,@RequestParam int size, @RequestParam(required = false) String order, @RequestParam(required = false) Boolean asc){
		PageRequest pageRequest = PageRequest.of(page, size);
		if(order != null && asc != null) {
			pageRequest = PageRequest.of(page, size,asc ? Sort.Direction.ASC : Sort.Direction.DESC,order);
		}
		return alarmregisterrespository.getPageAlarmRegister(pageRequest);
	}
	
	/*Retorna uma pagina de registro de alarmes de um determinado sensor*/
	@GetMapping("/probe/{probedescid}/alarm/register/page")
	public Page<Object[]> getPageByAlarm(@PathVariable (value = "probedescid") Long id,@RequestParam int page,@RequestParam int size, @RequestParam(required = false) String order, @RequestParam(required = false) Boolean asc){
		PageRequest pageRequest = PageRequest.of(page, size);
		if(order != null && asc != null) {
			pageRequest = PageRequest.of(page, size,asc ? Sort.Direction.ASC : Sort.Direction.DESC,order);
		}
		return alarmregisterrespository.getPageAlarmRegisterByProbe(id,pageRequest);
	}
}
