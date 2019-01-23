package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.andlucsil.probesserver.model.ProbesValues;
import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;
import br.com.andlucsil.probesserver.repository.ProbesValuesRepository;

@Controller
@RequestMapping("/probesvalues")
public class ProbesValuesController {

	@Autowired
	ProbesValuesRepository probesvaluesrepository;
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	//Retorna todas as leituras de todos os sensores
	@GetMapping("/")
	public String lista(Model model){
		model.addAttribute("reads",probesvaluesrepository.findAll());
		return "probesvalues/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
	//Retorna todas as leituras de um determinado sensor
	@GetMapping("/{sensor}")
	public String visualizarSensor(@PathVariable String sensor, Model model){
		model.addAttribute("reads", probesvaluesrepository.todasLeiturasSensor(sensor));
		return "probesvalues/lista_individual";
	}
	
	//Retorna o valor médio da leitura de um sensor
	@GetMapping("/{sensor}/avg")
	public String visualizarMedia(@PathVariable String sensor, Model model){
		model.addAttribute("avg", probesvaluesrepository.leituraMediaSensor(sensor));
		return "probesvalues/avg";
	}
	
	//Retorna o valor maximo da leitura de um sensor
	@GetMapping("/{sensor}/max")
	public String visualizarMaximo(@PathVariable String sensor, Model model){
		model.addAttribute("max", probesvaluesrepository.leituraMaximaSensor(sensor));
		return "probesvalues/max";
	}
	
	//Retorna o valor minimo da leitura de um sensor
	@GetMapping("/{sensor}/min")
	public String visualizarMinimo(@PathVariable String sensor, Model model){
		model.addAttribute("min", probesvaluesrepository.leituraMinimaSensor(sensor));
		return "probesvalues/min";
	}
	
	//Retorna as ultimas 5 leituras
	@GetMapping("/{sensor}/lastfives")
	public String visualizarUltimasCinco(@PathVariable String sensor, Model model){
		Pageable cincoUltimos = PageRequest.of(0, 5);
		model.addAttribute("reads", probesvaluesrepository.ultimasCincoLeituras(sensor, cincoUltimos));
		return "probesvalues/lista_individual";
	}
}
