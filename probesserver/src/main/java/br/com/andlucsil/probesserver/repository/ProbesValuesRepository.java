package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andlucsil.probesserver.model.ProbesValues;

public interface ProbesValuesRepository extends JpaRepository <ProbesValues, Long>{

	List<ProbesValues> findAll();
}
