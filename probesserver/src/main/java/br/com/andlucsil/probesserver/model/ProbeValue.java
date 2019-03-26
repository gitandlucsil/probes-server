package br.com.andlucsil.probesserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "probe_value")
public class ProbeValue implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "read_value", nullable = false)
    private int read_value;
    @Column(name = "read_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date read_date;
    @Column(name = "read_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date read_time;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "probedescription")
    private ProbeDescription probedescription;
    
    public ProbeValue() {
        this.read_date = new Date(System.currentTimeMillis());
        this.read_time = new Date(System.currentTimeMillis());
    }

    public int getRead_value() {
        return read_value;
    }

    public void setRead_value(int read_value) {
        this.read_value = read_value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public ProbeDescription getProbedescription() {
		return probedescription;
	}

	public void setProbedescription(ProbeDescription probedescription) {
		this.probedescription = probedescription;
	}
    
}
