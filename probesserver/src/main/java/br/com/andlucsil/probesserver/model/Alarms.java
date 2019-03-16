package br.com.andlucsil.probesserver.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "alarms")
public class Alarms implements Serializable{
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column (name = "value", nullable = false)
    private int value;
    @Column (name = "type", nullable = false)
    private boolean type; 
    @Column(name = "active", nullable = false)
    private boolean active;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "alarm")
    @JsonBackReference
    private List<AlarmRegister> alarm;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "probesidf")
    @JsonManagedReference
    private ProbesIdf probesidf;
    
    public Alarms() {
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
	public List<AlarmRegister> getAlarm() {
		return alarm;
	}
	public void setAlarm(List<AlarmRegister> alarm) {
		this.alarm = alarm;
	}
	public ProbesIdf getProbesidf() {
		return probesidf;
	}
	public void setProbesidf(ProbesIdf probesidf) {
		this.probesidf = probesidf;
	}
    
}