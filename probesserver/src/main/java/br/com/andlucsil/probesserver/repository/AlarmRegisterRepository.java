package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andlucsil.probesserver.model.AlarmRegister;

public interface AlarmRegisterRepository extends JpaRepository <AlarmRegister, Long>{

	/*Recuperar todos os registros de alarmes de um determinado sensor, retornando o indice do registro*/
	@Query("select ar from AlarmRegister ar, ProbeValue pv, ProbeDescription pdesc\n"+
		   "where pv.probedescription = pdesc.id\n"+
		   "and ar.probevalue = pv.id\n"+
		   "and pdesc.id = ?1")
	List<AlarmRegister> allAlarmsProbe(Long probedescid);
	
	/*Recuperar todos os registros de alarmes, retornando os objetos*/
	@Query("select a, pv, ar.alarm_value from AlarmRegister ar, Alarm a, ProbeValue pv\n"+
		   "where ar.alarm = a.id\n"+
		   "and ar.probevalue = pv.id")
	List<Object[]> allInfoRegisters();
	
	/*Recuperar todos os registros de alarmes de um determinado sensor, retornando os objetos*/
	@Query("select a, pv, ar.alarm_value from AlarmRegister ar, Alarm a, ProbeValue pv, ProbeDescription pdesc\n"+
			   "where ar.alarm = a.id\n"+
			   "and ar.probevalue = pv.id\n"+
			   "and pv.probedescription = pdesc.id\n"+
			   "and pdesc.id = ?1")
	List<Object[]> allInfoRegistersByProbe(Long probedescid);
	
	/*Recuperar todos os registros de alarmes de um determinado sensor, retornando os objetos*/
	@Query("select a, pv,ar.alarm_value from AlarmRegister ar, Alarm a, ProbeValue pv\n"+
			   "where ar.alarm = a.id\n"+
			   "and ar.probevalue = pv.id\n"+
			   "and ar.id = ?1")
	List<Object[]> allInfoRegisterById(Long id);
}
