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
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Modelo da classe Alarm
 * id - Chave primaria de um alarme
 * description - Descrição do alarme (Por exemplo, temperatura alta, umidade baixa)
 * value - O valor inteiro para ser atribuido um alarme
 * type - T:maior, F:menor, ou seja, se for maior, quando leitura >= value, gerara um registro de alarme,
 *        se for menor, quando leitura <= value, gerara um registro de alarme
 * active - T:alarme ativo, F:alarme desativo, ou seja, quando uma leitura gerar um alarme, marca true, caso
 *          o contrario, marca false
 * probedescription - Para qual sensor pertence o alarme  
 * */
@Entity
@Table(name = "alarm")
public class Alarm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "value", nullable = false)
	private int value;
	@Column(name = "type", nullable = false)
	private boolean type;
	@Column(name = "active", nullable = false)
	private boolean active;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "probedescription")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProbeDescription probedescription;
	
    public Alarm() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ProbeDescription getProbedescription() {
		return probedescription;
	}

	public void setProbedescription(ProbeDescription probedescription) {
		this.probedescription = probedescription;
	}
    
}
