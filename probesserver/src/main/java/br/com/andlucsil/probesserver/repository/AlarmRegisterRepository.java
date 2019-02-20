package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andlucsil.probesserver.model.AlarmRegister;

public interface AlarmRegisterRepository extends JpaRepository <AlarmRegister, Long>{

	/*Recuperar todos os alarmes de um determinado sensor*/
	@Query("select ar from AlarmRegister ar, ProbesValues pv, ProbesIdf pidf\n" + 
			"where pv.probesidf = pidf.id_value\n" + 
			"and ar.probesvalues = pv.id\n" + 
			"and pidf.description = ?1")
	List<AlarmRegister> todosAlarmesSensor(String sensor);
}
