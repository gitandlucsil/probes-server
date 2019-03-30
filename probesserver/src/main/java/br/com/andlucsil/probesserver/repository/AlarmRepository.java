package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andlucsil.probesserver.model.Alarm;

public interface AlarmRepository extends JpaRepository <Alarm, Long>{

	/*Recuperar todos os alarmes de um determinado sensor*/
	List<Alarm> findByProbedescriptionId(Long probedescriptionId);
}
