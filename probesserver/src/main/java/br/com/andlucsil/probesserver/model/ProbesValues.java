package br.com.andlucsil.probesserver.model;

import java.util.Date;

public class ProbesValues {

	private Long id;
	private ProbesIdf probesidf;
	private int read_value;
	private Date read_date;
	private Date read_time;
	
	public ProbesValues() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProbesIdf getProbesidf() {
		return probesidf;
	}
	public void setProbesidf(ProbesIdf probesidf) {
		this.probesidf = probesidf;
	}
	public int getRead_value() {
		return read_value;
	}
	public void setRead_value(int read_value) {
		this.read_value = read_value;
	}
	public Date getRead_date() {
		return read_date;
	}
	public void setRead_date(Date read_date) {
		this.read_date = read_date;
	}
	public Date getRead_time() {
		return read_time;
	}
	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}
	
	
}
