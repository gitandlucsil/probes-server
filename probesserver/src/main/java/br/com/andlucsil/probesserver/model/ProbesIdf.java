package br.com.andlucsil.probesserver.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProbesIdf {
	
	@Id
	private Long id;
	private String description;
	
	public ProbesIdf() {
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

}
