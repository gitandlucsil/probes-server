package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andlucsil.probesserver.model.Alarm;
import br.com.andlucsil.probesserver.model.ProbeValue;

public interface AlarmRepository extends JpaRepository <Alarm, Long>{

	/*Recuperar todos os alarmes de um determinado sensor*/
	List<Alarm> findByProbedescriptionId(Long probedescriptionId);
	
	/*Recuperar todos os alarmes ativos*/
	@Query("select a from Alarm a where a.active = true")
	List<Alarm> findAllActives();
	
	/*Recuperar todos os alarmes ativos de um determinado sensor*/
	@Query("select a from Alarm a, ProbeDescription pdesc\n"+
		   "where a.active = true\n"+
		   "and a.probedescription = pdesc.id\n"+
		   "and pdesc.id = ?1")
	List<Alarm> findAllActivesById(Long probedescription);
	
	/*Recupera se um determinado alarme esta ativo ou nao*/
	@Query("select a.active from Alarm a\n"+
		   "where a.id = ?1")
	Boolean findAlarmActive(Long alarmid);
	
}
