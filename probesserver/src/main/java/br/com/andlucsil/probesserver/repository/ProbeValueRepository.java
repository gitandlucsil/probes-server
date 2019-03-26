package br.com.andlucsil.probesserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andlucsil.probesserver.model.ProbeValue;

public interface ProbeValueRepository extends JpaRepository <ProbeValue, Long>{

}
