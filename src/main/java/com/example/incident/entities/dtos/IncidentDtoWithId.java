package com.example.incident.entities.dtos;

public class IncidentDtoWithId {

	private Integer incidentId;
	private String incidentName;
	
	public IncidentDtoWithId() {
	}

	public IncidentDtoWithId(Integer incidentId, String incidentName) {
		this.incidentId = incidentId;
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
}
