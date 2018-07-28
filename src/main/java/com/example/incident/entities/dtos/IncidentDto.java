package com.example.incident.entities.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class IncidentDto {

	@NotBlank(message = "incidentName cannot be null or blank")
	@Length(min = 5, message = "Minimum length should be 5")
	private String incidentName;
	
	public IncidentDto() {
		
	}

	public IncidentDto(String incidentName) {
		this.incidentName = incidentName;
	}

	public String getIncidentName() {
		return incidentName;
	}

	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}

}
