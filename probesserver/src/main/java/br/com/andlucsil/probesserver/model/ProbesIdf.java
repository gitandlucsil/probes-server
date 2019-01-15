package br.com.andlucsil.probesserver.model;

import java.util.List;

public class ProbesIdf {
	
	private int id_value;
	private String description;
	private List<ProbesValues> probesvalues;
	
	public ProbesIdf() {
		
	}

	public int getId_value() {
		return id_value;
	}

	public void setId_value(int id_value) {
		this.id_value = id_value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProbesValues> getProbesvalues() {
		return probesvalues;
	}

	public void setProbesvalues(List<ProbesValues> probesvalues) {
		this.probesvalues = probesvalues;
	}

}
