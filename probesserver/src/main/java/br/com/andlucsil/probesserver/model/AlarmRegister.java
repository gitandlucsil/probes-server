package br.com.andlucsil.probesserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Modelo da classe AlarmRegister
 * id - Chave primaria de um registro de alarme
 * alarm - Alarme que pertence o registro
 * probevalue - Leitura que pertence o registro
 * */
@Entity
@Table(name = "alarm_register")
public class AlarmRegister implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alarm")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Alarm alarm;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "probevalue")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProbeValue probevalue;
    @Column(name = "alarm_value")
    private int alarm_value;
    
	public AlarmRegister() {
	}
	
	public AlarmRegister(Alarm a, ProbeValue pv) {
		this.alarm = a;
		this.probevalue = pv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public ProbeValue getProbevalue() {
		return probevalue;
	}

	public void setProbevalue(ProbeValue probevalue) {
		this.probevalue = probevalue;
	}

	public int getAlarm_value() {
		return alarm_value;
	}

	public void setAlarm_value(int alarm_value) {
		this.alarm_value = alarm_value;
	}
    
}
