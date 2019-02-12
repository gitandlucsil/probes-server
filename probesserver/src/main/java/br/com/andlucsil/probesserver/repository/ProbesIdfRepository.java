package br.com.andlucsil.probesserver.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andlucsil.probesserver.model.Alarms;
import br.com.andlucsil.probesserver.model.ProbesIdf;
public interface ProbesIdfRepository extends JpaRepository <ProbesIdf, Long>{


}
