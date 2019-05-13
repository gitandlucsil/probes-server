package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andlucsil.probesserver.model.ProbeValue;

public interface ProbeValueRepository extends JpaRepository <ProbeValue, Long>{
	
	/*Recuperar todas as leituras de um determinado sensor*/
	List<ProbeValue> findByProbedescriptionId(Long probedescriptionId);
	
	/*Recuperar a leitura media de um determinado sensor*/
	@Query("select TRUNC(AVG(pv.read_value),2) from ProbeValue pv, ProbeDescription pdesc\n" + 
			"where pv.probedescription = pdesc.id\n" + 
			"and pdesc.id = ?1")
	Double averageReadProbe(Long id);
	
	/*Recuperar a leitura maxima de um determinado sensor*/
	@Query("select MAX(pv.read_value) from ProbeValue pv, ProbeDescription pdesc\n" + 
			"where pv.probedescription = pdesc.id\n" + 
			"and pdesc.id = ?1")
	Integer maxReadProbe(Long id);
	
	/*Recuperar a leitura minima de um determinado sensor*/
	@Query("select MIN(pv.read_value) from ProbeValue pv, ProbeDescription pdesc\n" + 
			"where pv.probedescription = pdesc.id\n" + 
			"and pdesc.id = ?1")
	Integer minReadProbe(Long id);
	
	/*Recupera as ultimas 5 leituras de um ultimo sensor*/
	@Query("select pv from ProbeValue pv, ProbeDescription pdesc\n" + 
			"where pv.probedescription = pdesc.id\n" + 
			"and pdesc.id = :probe "+
			"order by pv.id desc")
	List<ProbeValue> lastFiveReads(@Param("probe")Long probe, Pageable pageable);
	
	/*Retorna uma pagina com um numero de leitura desejado pelo usuario*/
	@Query("select pv from ProbeValue pv, ProbeDescription pdesc\n" + 
			"where pv.probedescription = pdesc.id\n" + 
			"and pdesc.id = :probe")
	Page<ProbeValue> getPageProbeValue(@Param("probe")Long probe, Pageable pageable);
}
