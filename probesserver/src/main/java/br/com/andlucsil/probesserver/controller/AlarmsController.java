package br.com.andlucsil.probesserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.model.Alarms;
import br.com.andlucsil.probesserver.repository.AlarmsRepository;
import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;

@Controller
@RequestMapping("/alarms")
public class AlarmsController {
	
	@Autowired
	AlarmsRepository alarmRepository;
	@Autowired
	ProbesIdfRepository probesidfRepository;
	
	@GetMapping("/")
	public String lista(Model model){
		//${dados} será a variável disponível no template thymeleaf
		model.addAttribute("dados",alarmRepository.findAll());
		return "alarms/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
	@GetMapping("/novo")
	public String novo(Model model){
		model.addAttribute("alarm", new Alarms());
		model.addAttribute("probes", probesidfRepository.findAll());
		return "alarms/form";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Alarms alarm, BindingResult erros){
		System.out.println(alarm.getValue());
		System.out.println(alarm.getDescription());
		System.out.println(alarm.isType());
		//System.out.println(alarm.getProbesidf().getDescription());
		alarmRepository.save(alarm);
		return "redirect:/alarms/";
	}
	
	@GetMapping("/{codigo}")
	public String visualizar(@PathVariable Long codigo, Model model){
		model.addAttribute("alarm", alarmRepository
							.findById(codigo).orElse(new Alarms()));
		model.addAttribute("probes", probesidfRepository.findAll());
		return "alarms/form";
	}
	
	@GetMapping("/remover/{codigo}")
	public String remover(@PathVariable Long codigo, Model model){
		alarmRepository.deleteById(codigo);
		return "redirect:/alarms/";
	}
}
