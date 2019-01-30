package br.com.andlucsil.probesserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andlucsil.probesserver.model.AlarmRegister;

public interface AlarmRegisterRepository extends JpaRepository <AlarmRegister, Long>{

}
