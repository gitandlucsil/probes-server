package br.com.andlucsil.probesserver.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProbesIdf {
	
	@Id
	private Long id_value;
	private String description;
	
	public ProbesIdf() {
	}

	public Long getId_value() {
		return id_value;
	}

	public void setId(Long id) {
		this.id_value = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
