package br.com.andlucsil.probesserver.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.model.ProbesIdf;
import br.com.andlucsil.probesserver.model.ProbesValues;
import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;
import br.com.andlucsil.probesserver.repository.ProbesValuesRepository;

@RestController
@RequestMapping("/rest/probesvalues")
public class ProbesValuesRestController {

	@Autowired
	ProbesValuesRepository probesvaluesrepository;
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
	//Retorna todas as leituras de todos os sensores
	@GetMapping("/")
	public List<ProbesValues> lista(Model model){
		return probesvaluesrepository.findAll();
	}
	
	//Retorna as leituras de um determinado id
	/*@GetMapping("/{id}")
	public ProbesValues visualizar(@PathVariable(value = "id") Long id) {
		return probesvaluesrepository.findById(id).orElse(new ProbesValues());
	}*/
	
	//Retorna todas as leituras de um determinado sensor
	@GetMapping("/{sensor}")
	public List<ProbesValues> visualizarSensor(@PathVariable String sensor){
		return probesvaluesrepository.todasLeiturasSensor(sensor);
	}
	
	//Retorna as ultimas 5 leituras
	@GetMapping("/{sensor}/lastfives")
	public List<ProbesValues> visualizarUltimasCinco(@PathVariable String sensor){
		Pageable cincoUltimos = PageRequest.of(0, 5);
		return probesvaluesrepository.ultimasCincoLeituras(sensor, cincoUltimos);
	}
	
	//Retorna o valor médio da leitura de um sensor
	@GetMapping("/{sensor}/avg")
	public Double visualizarMedia(@PathVariable String sensor){
		return probesvaluesrepository.leituraMediaSensor(sensor);
	}
	
	//Retorna o valor maximo da leitura de um sensor
	@GetMapping("/{sensor}/max")
	public Integer visualizarMaximo(@PathVariable String sensor){
		return probesvaluesrepository.leituraMaximaSensor(sensor);
	}
	
	//Retorna o valor minimo da leitura de um sensor
	@GetMapping("/{sensor}/min")
	public Integer visualizarMinimo(@PathVariable String sensor){
		return probesvaluesrepository.leituraMinimaSensor(sensor);
	}
}
