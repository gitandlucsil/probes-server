package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andlucsil.probesserver.model.ProbesValues;

public interface ProbesValuesRepository extends JpaRepository <ProbesValues, Long>{

	/*Recuperar todas as leituras de um determinado sensor*/
	@Query("select pv from ProbesValues pv, ProbesIdf pidf\n" + 
			"where pv.probesidf = pidf.id_value\n" + 
			"and pidf.description = ?1")
	List<ProbesValues> todasLeiturasSensor(String sensor);
}
