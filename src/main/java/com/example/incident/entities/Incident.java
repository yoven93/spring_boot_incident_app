package com.example.incident.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "INCIDENTS")
public class Incident implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INCIDENT_ID")
	private Integer incidentId;
	
	@Column(name = "INCIDENT_NAME", nullable = false, length = 160)
	private String incidentName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INCIDENT_DATE", nullable = false)
	private Date incidentDate;
	
	public Incident() {
		
	}
	
	public Incident(String incidentName) {
		this.incidentName = incidentName;
	}

	public Integer getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentName() {
		return incidentName;
	}

	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}

	public Date getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}
	
	@PrePersist
	public void initDate() {
		this.incidentDate = new Date();
	}

}
