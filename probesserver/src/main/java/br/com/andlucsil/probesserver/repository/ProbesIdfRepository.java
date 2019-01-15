package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andlucsil.probesserver.model.ProbesIdf;

public interface ProbesIdfRepository extends JpaRepository <ProbesIdf, Long>{

	List<ProbesIdf> findAll();
}
