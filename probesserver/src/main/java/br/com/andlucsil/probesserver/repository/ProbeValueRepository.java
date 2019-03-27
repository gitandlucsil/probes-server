package br.com.andlucsil.probesserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andlucsil.probesserver.model.ProbeValue;

public interface ProbeValueRepository extends JpaRepository <ProbeValue, Long>{
	//List<ProbeValue> findByProbeDescriptionId(Long probedescriptionId);
}
