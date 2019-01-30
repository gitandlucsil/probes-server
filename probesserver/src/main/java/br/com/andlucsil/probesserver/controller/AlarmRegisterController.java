package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		model.addAttribute("dados",alarmRegisterRepository.findAll());
		return "alarmregister/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
}
