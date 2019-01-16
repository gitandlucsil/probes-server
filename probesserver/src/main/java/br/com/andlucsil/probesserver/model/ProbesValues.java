package br.com.andlucsil.probesserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProbesValues {

	@Id
	private Long id;
	private int probesidf;
	private int read_value;
	private Date date;
	private Date time;
	
	public ProbesValues() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getProbesidf() {
		return probesidf;
	}
	public void setProbesidf(int probesidf) {
		this.probesidf = probesidf;
	}
	public int getRead_value() {
		return read_value;
	}
	public void setRead_value(int read_value) {
		this.read_value = read_value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
