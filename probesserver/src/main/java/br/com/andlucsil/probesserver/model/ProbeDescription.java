/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andlucsil.probesserver.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "probe_description")
public class ProbeDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    /*@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "probesidf")
    @JsonBackReference
    private List<Alarms> alarms;*/

    public ProbeDescription() {
    }

    public ProbeDescription(Long id, String description) {
        this.id = id;
        this.description = description;
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


	/*public List<Alarms> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarms> alarms) {
		this.alarms = alarms;
	}*/
	
}