package br.com.andlucsil.probesserver.controller;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.Alarm;
import br.com.andlucsil.probesserver.model.AlarmRegister;
import br.com.andlucsil.probesserver.model.ProbeDescription;
import br.com.andlucsil.probesserver.model.ProbeValue;
import br.com.andlucsil.probesserver.repository.AlarmRegisterRepository;
import br.com.andlucsil.probesserver.repository.AlarmRepository;
import br.com.andlucsil.probesserver.repository.ProbeDescriptionRepository;
import br.com.andlucsil.probesserver.repository.ProbeValueRepository;
import br.com.andlucsil.probesserver.report.GenerateReport;
import br.com.andlucsil.probesserver.report.connection.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

@RestController
public class ProbeValueController {

	@Autowired
	ProbeValueRepository probevaluerepository;
	@Autowired
	ProbeDescriptionRepository probedescriptionrepository;
	@Autowired
	AlarmRepository alarmrepository;
	@Autowired
	AlarmRegisterRepository alarmregisterrepository;
	
	/*Retorna as leituras de todos os sensores*/
	@GetMapping("/probevalue")
	public List<ProbeValue> getAllProbesValue(){
		return probevaluerepository.findAll();
	}
	/*Retorna as leituras de um determinado sensor*/
	@GetMapping("/probedesc/{probedescid}/probevalue")
	public List<ProbeValue> getAllProbesValuesByDescId(@PathVariable (value = "probedescid") Long id){
		return probevaluerepository.findByProbedescriptionId(id);
	}
	/*Insere uma leitura para um determinado sensor*/
	@PostMapping("/probedesc/{probedescid}/probevalue")
	public ProbeValue saveProbeValue(@PathVariable (value = "probedescid") Long id, @Valid @RequestBody ProbeValue probevalue){
		return probedescriptionrepository.findById(id).map(probedesc -> {
			probevalue.setProbedescription(probedesc);
			return setAlarmByRead(probevalue);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + id + " não encontrado"));
	}
	/*Altera a leitura para um determinado sensor*/
	@PutMapping("/probedesc/{probedescid}/probevalue/{probevalueid}")
	public ProbeValue updateProbeValue(@PathVariable (value = "probedescid") Long desc_id, @PathVariable (value = "probevalueid") Long value_id, @Valid @RequestBody ProbeValue probevalue) {
		if(!probedescriptionrepository.existsById(desc_id)) {
			throw new ResourceNotFoundException("ProbeDescriptionId " + desc_id + " não encontrado");
		} else {
			probevalue.setProbedescription(probedescriptionrepository.findById(desc_id).orElse(new ProbeDescription()));
		}
		return probevaluerepository.findById(value_id).map(pvalue -> {
			pvalue.setRead_value(probevalue.getRead_value());
			pvalue.setProbedescription(probevalue.getProbedescription());
			return probevaluerepository.save(pvalue);
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + value_id + " não encontrado"));
	}
	/*Deleta a leitura para um determinado sensor*/
	@DeleteMapping("/probevalue/{id}")
	public ResponseEntity<?> deleteProbeValue(@PathVariable Long id){
		return probevaluerepository.findById(id).map(probevalue -> {
			probevaluerepository.delete(probevalue);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("ProbeId " + id + " não encontrado"));
	}
	/*Retorna o valor médio da leitura de um sensor*/
	@GetMapping("/probedesc/{probedescid}/avg")
	public Double viewAvgValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.averageReadProbe(id);
	}
	/*Retorna o valor máximo da leitura de um sensor*/
	@GetMapping("/probedesc/{probedescid}/max")
	public Integer viewMaxValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.maxReadProbe(id);
	}
	/*Retorna o valor minimo da leitura de um sensor*/
	@GetMapping("/probedesc/{probedescid}/min")
	public Integer viewMinValue(@PathVariable (value = "probedescid") Long id) {
		return probevaluerepository.minReadProbe(id);
	}
	/*Retorna as ultimas cinco leituras*/
	@GetMapping("/probedesc/{probedescid}/lastfives")
	public List<ProbeValue> getLastFiveReads(@PathVariable Long probedescid){
		Pageable lastfives = PageRequest.of(0, 5);
		return probevaluerepository.lastFiveReads(probedescid, lastfives);
	}
	/*Retorna uma pagina com um numero de leitura desejado pelo usuario*/
	@GetMapping("/probedesc/{probedescid}/page")
	public Page<ProbeValue> getPage(@PathVariable Long probedescid,@RequestParam int page,@RequestParam int size, @RequestParam(required = false) String order, @RequestParam(required = false) Boolean asc){
		PageRequest pageRequest = PageRequest.of(page, size);
		if(order != null && asc != null) {
			pageRequest = PageRequest.of(page, size,asc ? Sort.Direction.ASC : Sort.Direction.DESC,order);
		}
		return probevaluerepository.getPageProbeValue(probedescid, pageRequest);
	}
	/*Método para verificar se uma leitura gerou algum alarme, caso sim, inserir no alarmregister*/
	public ProbeValue setAlarmByRead(ProbeValue probevalue) {
		probevaluerepository.save(probevalue);
		List<Alarm> alarms = alarmrepository.findAll();
		for(Alarm a : alarms) {
			if(a.getProbedescription().getId() == probevalue.getProbedescription().getId()) {
				if(a.isType()) { //Se for marcado para ser um alarme de valor maior
					if(probevalue.getRead_value() >= a.getValue()) { //Se a leitura do sensor for maior que o alarme
						AlarmRegister ar = new AlarmRegister(a,probevalue);//Instancia o novo objeto de alarme register
						ar.setAlarm_value(a.getValue()); //Seta o valor do alarme para registro, com o valor do alarme atual
						alarmregisterrepository.save(ar); //Salva o alarm register
						a.setActive(true); //Seta o alarme como ativo
						alarmrepository.save(a); //Atualiza o estado do alarme
					} else {
						a.setActive(false); //Se a leitura do sensor for menor que o alarme, seta como desativo
						alarmrepository.save(a);
					}
				} else { //Se for marcado para ser um alarme de valor menor
					if(probevalue.getRead_value() <= a.getValue()) {//Se a leitura do sensor for menor que o alarme
						AlarmRegister ar = new AlarmRegister(a,probevalue);//Instancia o novo objeto de alarme register
						ar.setAlarm_value(a.getValue()); //Seta o valor do alarme para registro, com o valor do alarme atual
						alarmregisterrepository.save(ar); //Salva o alarm register
						a.setActive(true); //Seta o alarme como ativo
						alarmrepository.save(a); //Atualiza o estado do alarme
					} else {
						a.setActive(false); //Se a leitura do sensor for mmenor que o alarme, seta como desativo
						alarmrepository.save(a);
					}
				}
			}
		}
		return probevalue;
	}
	
	/*Gera um relatorio com o resumo detalhado por horario de um determinado dia, contendo valores minimo, maximo e medio de cada horario*/
	@GetMapping("/probedesc/{probedescid}/report/time")
	public void getReportByProbeDescByTime(@PathVariable (value = "probedescid") Integer id, @RequestParam String date) throws ParseException, JRException{
		Connection connection = new ConnectionFactory().getConnection(); //Conecta com o banco
		JasperCompileManager.compileReportToFile("src/main/resources/reports/probe_info_time.jrxml"); //Compila o arquivo gerado no iReports
		Map<String, Object> params = new HashMap<String, Object>(); //Inicializa um Map chave-valor, usado como parametro do relatorios
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataformat = sdf.parse(date); //Coverte a data recebida como parametro da requisicao URL
		Integer probe = id;
		params.put("PROBE", probe);
		params.put("DATE", dataformat);
		GenerateReport generateReport = new GenerateReport("src/main/resources/reports/probe_info_time",params,connection);
		generateReport.generatePdf();
	}
}
