package br.com.andlucsil.probesserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andlucsil.probesserver.model.Alarms;

public interface AlarmsRepository extends JpaRepository <Alarms, Long>{

}
