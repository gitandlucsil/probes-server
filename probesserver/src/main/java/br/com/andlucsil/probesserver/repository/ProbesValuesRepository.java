package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andlucsil.probesserver.model.ProbesValues;

public interface ProbesValuesRepository extends JpaRepository <ProbesValues, Long>{

	/*Recuperar todas as leituras de um determinado sensor*/
	@Query("select pv from ProbesValues pv, ProbesIdf pidf\n" + 
			"where pv.probesidf = pidf.id_value\n" + 
			"and pidf.description = ?1")
	List<ProbesValues> todasLeiturasSensor(String sensor);
	
	/*Recuperar a leitura media de um determinado sensor*/
	@Query("select AVG(pv.read_value) from ProbesValues pv, ProbesIdf pidf\n" + 
			"where pv.probesidf = pidf.id_value\n" + 
			"and pidf.description = ?1")
	Double leituraMediaSensor(String sensor);
	
	/*Recupera as ultimas 5 leituras de um ultimo sensor*/
	@Query("select pv from ProbesValues pv, ProbesIdf pidf\n" + 
			"where pv.probesidf = pidf.id_value\n" + 
			"and pidf.description = :sensor "+
			"order by pv.id desc")
	List<ProbesValues> ultimasCincoLeituras(@Param("sensor")String sensor, Pageable pageable);
	
}
