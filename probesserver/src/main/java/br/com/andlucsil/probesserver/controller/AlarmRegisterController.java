package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.repository.AlarmRegisterRepository;

@Controller
@RequestMapping("/alarmregister")
public class AlarmRegisterController {

	@Autowired
	AlarmRegisterRepository alarmRegisterRepository;
	
	@GetMapping("/")
	public String lista(Model model){
		//${dados} será a variável disponível no template thymeleaf
		model.addAttribute("alarms",alarmRegisterRepository.findAll());
		return "alarmregister/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
	//Retorna todos os alarmes de um determinado sensor
	@GetMapping("/{sensor}")
	public String visualizarSensor(@PathVariable String sensor, Model model){
		model.addAttribute("alarms", alarmRegisterRepository.todosAlarmesSensor(sensor));
		return "alarmregister/lista_individual";
	}
	
}
